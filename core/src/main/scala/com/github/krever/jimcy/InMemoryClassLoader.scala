package com.github.krever.jimcy

import java.security.SecureClassLoader


class InMemoryClassLoader(private val classes :scala.collection.Map[String, InMemoryJavaClassObject])
  extends SecureClassLoader() {

  protected override def findClass(name: String): Class[_] = {
    classes.get(name) match {
      case Some(classObject) =>
        val bytes = classObject.bytes
        super.defineClass(name, bytes, 0, bytes.length);
      case None => throw new ClassNotFoundException(name)
    }
  }

  def allClasses :Map[String, Class[_]] = classes.map( entry => entry._1 -> loadClass(entry._1)).toMap

}
