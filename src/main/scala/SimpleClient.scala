import cats.effect.{ExitCode, IO, IOApp}
import cats.effect.unsafe.implicits.global
import org.http4s.blaze.client.BlazeClientBuilder
import org.http4s.client.Client
import org.http4s.implicits.http4sLiteralsSyntax

/**
 * We need to run through client/server fundamentals again
 */

object SimpleClient extends IOApp{
  def callEffect(client: Client[IO], str: String): IO[String] = {
    // note that the client expects a String and if we want a different data type, we have to map it
    client.expect[String](uri"http://localhost:8080/length/" / str)
  }

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO].resource
      // difference between client vs. server syntax, '.use' needed to allocate actual client and supply it to lambda function
      .use { client =>
        // note that the unsafeRunSync brings back memories of Async Run -> so is it related to Asynchronous Ops
        println(callEffect(client, "Baeldung").unsafeRunSync())
        // client released when below effect is called
        IO.unit
      }
      .as(ExitCode.Success)

}
