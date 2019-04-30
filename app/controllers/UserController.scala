package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.UserService

@Singleton
class UserController @Inject()(userService: UserService, cc: ControllerComponents) extends AbstractController(cc) {

  def create(userId: Int, studioId: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val user = userService.create(userId, studioId)

    Ok(Json.obj("result" -> user))
  }

  def find(userId: Int, studioId: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val user = userService.find(userId, studioId)
    user match {
      case None => NotFound(Json.obj("error" -> "NOT_FOUND"))
      case Some(_) => Ok(Json.obj("result" -> user))
    }
  }
}
