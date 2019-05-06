package modules

import com.google.inject.AbstractModule
import repositories.{UserRepository, UserRepositoryImpl}
import services.{UserService, UserServiceImpl}

/** https://www.playframework.com/documentation/2.7.x/ScalaDependencyInjection#Binding-annotations **/

class UserModule extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[UserService]).to(classOf[UserServiceImpl])
    bind(classOf[UserRepository]).to(classOf[UserRepositoryImpl])
  }
}
