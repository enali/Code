package example.extralib

import java.io.File
import java.nio.charset.StandardCharsets

import scala.concurrent.Await
import scala.concurrent.duration._

import courier._
import Defaults._
import javax.mail.internet.MimeBodyPart

class EmailClient private (
                   emailHostUser: String,
                   emailHostPassword: String,
                   emailHost: String,
                   emailHostPort: Int,
                   defaultFromEmail: String) {

  private val mailer = Mailer(emailHost, emailHostPort)
    .auth(true)
    .as(emailHostUser, emailHostPassword)()

  /**
    * 发送邮件
    *
    * @param subject 主题
    * @param content 内容, 可以是html
    * @param toEmails 目标邮箱
    * @param fromEmail 发送邮箱
    * @param ccRecipients 抄送
    * @param bccRecipients 盲抄送, 指被抄送的邮箱看不到彼此
    * @param attachments 附件
    * @param contentType 内容类型, plain或html
    * @param duration 等待发送超时
    */
  def sendEmail(
                 subject: String,
                 content: String,
                 toEmails: Seq[String],
                 fromEmail: String = defaultFromEmail,
                 ccRecipients: Seq[String] = Seq.empty[String],
                 bccRecipients: Seq[String] = Seq.empty[String],
                 attachments: Seq[String] = Seq.empty[String],
                 contentType: String = "plain",  // 或者"html"
                 duration: Duration = 5.seconds
               ): Unit  = {
    // not use the mp.text or mp.html api, because of the charset problem
    val mbp1 = new MimeBodyPart()
    mbp1.setText(content, "utf-8", contentType)
    var mp = Multipart(Seq(mbp1))
    for (att <- attachments) {
      mp = mp.attach(new File(att))
    }

    // TODO: 添加参数验证
    val future = mailer(
      Envelope.from(fromEmail.addr)
        .to(toEmails.map(_.addr): _*)
        .cc(ccRecipients.map(_.addr): _*)
        .bcc(bccRecipients.map(_.addr): _*)
        .subject(subject, StandardCharsets.UTF_8)
        .content(mp)
    )
    Await.result(future, duration)
  }
}

object EmailClient {

//  def apply(conf: Conf): EmailClient = new EmailClient(
//    conf.getStringOrElse("config.email.host.user", EMAIL_HOST_USER),
//    conf.getStringOrElse("config.email.host.password", EMAIL_HOST_PASSWORD),
//    conf.getStringOrElse("config.email.host", EMAIL_HOST),
//    conf.getIntOrElse("config.email.host.port", EMAIL_HOST_PORT),
//    conf.getStringOrElse("config.email.default.from", DEFAULT_FROM_EMAIL)
//  )

  def apply(
             emailHostUser: String,
             emailHostPassword: String,
             emailHost: String,
             emailHostPort: Int,
             defaultFromEmail: String): EmailClient =
    new EmailClient(emailHostUser, emailHostPassword, emailHost, emailHostPort, defaultFromEmail)

  def main(args: Array[String]): Unit = {
    val client = EmailClient()
    val content = "<h2>中国人, world</h2>"
    client.sendEmail("中国, test from scala", content, Seq("xx@yy.com"), contentType = "html")
  }
}
