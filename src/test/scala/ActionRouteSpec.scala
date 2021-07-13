import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ActionRouteSpec extends AnyWordSpec with Matchers with ScalaFutures with ScalatestRouteTest {
  lazy val testKit = ActorTestKit()

  implicit def typedSystem = testKit.system

  override def createActorSystem(): akka.actor.ActorSystem =
    testKit.system.classicSystem


}
