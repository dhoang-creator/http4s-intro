import cats.effect.IO
import cats.effect.unsafe.IORuntime
import org.http4s.HttpRoutes
import org.http4s.Method.GET
import org.http4s.Uri.Path.Root
import org.http4s.dsl.io.Ok

/*
  Note -> HttpRoutes[F] is alias for Kleisli[OptionT[F, *], Request, Response]
  Remember that Kleisli composition is simply the transformation of a non-monadic to monadic value
  Here, we are doing the same but the output monad is OptionT which is an Effectual data type?

  Kleisli[OptionT[F, *], Request, Response] == Request => F[Option[Response]] (where F is effectual op)
 */

object Endpoint {

  /**
   * Simple Endpoint example that returns length of string passed as param
   */

  // In order to define route, we have to define IORuntime
  implicit val runtime: IORuntime = cats.effect.unsafe.IORuntime.global

  val route: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "length" / str => Ok(str.length.toString)
  }

}
