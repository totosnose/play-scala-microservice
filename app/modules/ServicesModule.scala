package modules

import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import services.{UserService, UserServiceImpl}


class ServicesModule extends Module {
  def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(
      bind[UserService].to[UserServiceImpl]
    )
  }
}
