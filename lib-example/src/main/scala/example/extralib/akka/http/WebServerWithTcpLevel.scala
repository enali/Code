package example.extralib.akka.http

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.io.Tcp._
import akka.io.{IO, Tcp}

class Server6 extends Actor with ActorLogging {
  implicit val system = ActorSystem()

  // -> manager
  // 向TcpManager发送Bind消息
  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 0))

  override def receive: Receive = {
    // <- manager
    // TcpManager回复Bound消息, 表示绑定成功
    case b @ Bound(localAddress) =>
      log.info(s"Received msg $b")
      context.parent ! b

      // <- manager
      // TcpManager回复CommandFailed消息, 表示绑定失败
    case CommandFailed(_: Bind) =>
      log.info("Bind failed. then will stop self")
      context.stop(self)

      // client连接server成功时, (client/server都会收到此消息)
      // <- workerActor
    case c @ Connected(remote, local) =>
      val handler = context.actorOf(Props[SimplisticHandler])  // 实例化子actor
      val connection = sender()
      // 将子actor注册为通信的actor
      connection ! Register(handler)
  }
}

class SimplisticHandler extends Actor {

  override def receive: Receive = {
    case Received(data) => sender() ! Write(data)

    case PeerClosed => context.stop(self)
  }
}
