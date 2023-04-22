import Endpoint._
import cats.effect.ExitCode
import org.http4s.Response
import org.http4s.Uri.Path.Root
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.Router

// Question to be raised, can we compartmentalise all of the library dependency imports to a single file and are there pros and cons to importing individual libraries vs wildcard
import cats.data.Kleisli
import cats.effect.{IO, IOApp}
import org.http4s.{HttpRoutes, Request}
import org.http4s.Method.GET
import org.http4s.Status.Ok

object SimpleServer extends IOApp {
  // Can we not simply take this from Endpoints? -> Be more modular?
  val route: HttpRoutes[IO] = HttpRoutes.of[IO] {
    // If the business logic behind the Http Method is more complex, can we not compartmentalise it in another area?
    case GET -> Root / "length" / str => Ok(str.length.toString)
  }

  // Note that this is the Response Data Type
  val app: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "/" -> route
  ).orNotFound

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(app)
      // .resource returns actual Server -> side effect
      .resource
      // .useForever allocates the server ->
      // binds port 8080 to our app localhost
      .useForever
      .as(ExitCode.Success)

}
