package pl.krever.jimcy

import javax.tools.JavaFileObject.Kind

import org.specs2.mutable.Specification


class InMemoryJavaClassObjectTest extends Specification {

  "InMemoryJavaClassObject" should {
    "save bytes from input" in {
      val classObject  = InMemoryJavaClassObject("testName", Kind.CLASS)
      val bytes = "test".toCharArray.map(_.toByte)

      classObject.openOutputStream.write(bytes)
      val objectBytes = classObject.bytes

      objectBytes must beEqualTo(bytes)

    }
  }

}
