import cats.effect.{ExitCode, IO, IOApp}
import cats.effect.unsafe.implicits.global
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client
import org.http4s.implicits.http4sLiteralsSyntax

/**
 * We need to run through client/server fundamentals again
 */

object SimpleClient extends IOApp{
  def callEffect(client: Client[IO], str: String): IO[String] =
    client.expect[String](uri"http://localhost:8080/length/" / str)

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO].resource
      .use { client =>
        // note that the unsafeRunSync brings back memories of Async Run -> so is it related to Asynchronous Ops
        println(callEffect(client, "Baeldung").unsafeRunSync())
        IO.unit
      }
      .as(ExitCode.Success)

}
