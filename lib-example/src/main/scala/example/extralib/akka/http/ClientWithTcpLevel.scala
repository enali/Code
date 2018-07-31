package example.extralib.akka.http

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString

object ClientWithTcpLevel {
  def props(remote: InetSocketAddress, replies: ActorRef) =
    Props(new ClientWithTcpLevel(remote, replies))
}

class ClientWithTcpLevel(remote: InetSocketAddress, listener: ActorRef) extends Actor with ActorLogging {

  import Tcp._
  import context.system

  // 发消息给managerActor
  IO(Tcp) ! Connect(remote)

  def receive: PartialFunction[Any, Unit] = {
    // manager -> Client2
    case CommandFailed(_: Connect) ⇒
      listener ! "connect failed"
      context stop self

      // workerActor -> Client2
    case c @ Connected(remote, local) ⇒
      listener ! c
      val connection = sender()  // 将发送者保存到变量, 注意sender()是函数调用
      connection ! Register(self)
      context become {  // 切换状态
        case data: ByteString ⇒  // 遇到此类型消息, 则写数据
          connection ! Write(data)
        case CommandFailed(w: Write) ⇒  // 写失败的消息
          // O/S buffer was full
          listener ! "write failed"
        case Received(data) ⇒  // 当收到数据时
          listener ! data
        case "close" ⇒
          connection ! Close
        case _: ConnectionClosed ⇒  // 当连接关闭时
          listener ! "connection closed"
          context stop self
      }
  }
}

