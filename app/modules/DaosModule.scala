package modules

import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}
import repositories.dao.{UserDAO, UserDAOImpl}

class DaosModule extends Module {
  def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(
      bind[UserDAO].to[UserDAOImpl]
    )
  }
}
