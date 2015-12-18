package com.github.krever.jimcy

import org.specs2.mutable.Specification

/**
  * Created by krever on 12/12/15.
  */
class JavaClassNameFinderSpec extends Specification {

  "JavaClassNameFinder" should {

    "find name in simple class" in {
      val source = "public class TestClass { }"
      val name = JavaClassNameFinder.findName(source)
      name shouldEqual "TestClass"
    }

    "find name in extending class" in {
      val source = "public class TestClass extends Object { }"
      val name = JavaClassNameFinder.findName(source)
      name shouldEqual "TestClass"
    }

    "find name in importing class" in {
      val source =
        """
          |import java.lang.String
          |public class TestClass { }""".stripMargin
      val name = JavaClassNameFinder.findName(source)
      name shouldEqual "TestClass"
    }
  }
}
