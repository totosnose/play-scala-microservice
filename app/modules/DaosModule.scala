//package modules
//
//import play.api.inject.{Binding, Module}
//import play.api.{Configuration, Environment}
//import repositories.{UserRepository, UserRepositoryImpl}
//
//class DaosModule extends Module {
//  def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
//    Seq(
//      bind[UserRepository].to[UserRepositoryImpl]
//    )
//  }
//}
