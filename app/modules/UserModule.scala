package modules

import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import repositories.{UserRepository, UserRepositoryImpl}
import services.{UserService, UserServiceImpl}

/** https://www.playframework.com/documentation/2.7.x/ScalaDependencyInjection#Binding-annotations **/
// todo abstract module
class UserModule extends Module {
  def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(
      bind[UserService].to[UserServiceImpl],
      bind[UserRepository].to[UserRepositoryImpl]
    )
  }
}
