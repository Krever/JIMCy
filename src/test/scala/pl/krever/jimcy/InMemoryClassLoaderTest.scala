package pl.krever.jimcy

import org.specs2.mock.Mockito
import org.specs2.mutable.Specification


class InMemoryClassLoaderTest extends Specification with Mockito {

  "InMemoryClassLoader" should {

    "throw ClassNotFoundException when class not present" in {

      val classLoader = new InMemoryClassLoader(Map())

      classLoader.loadClass("class3") must throwA[ClassNotFoundException]

    }
  }



}
