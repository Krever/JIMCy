package pl.krever.jimcy

import java.security.SecureClassLoader


protected[jimcy] class InMemoryClassLoader(private val classes :scala.collection.Map[String, InMemoryJavaClassObject])
  extends SecureClassLoader() {

  protected override def findClass(name: String): Class[_] = {
    classes.get(name) match {
      case Some(classObject) =>
        val bytes = classObject.bytes
        super.defineClass(name, bytes, 0, bytes.length);
      case None => throw new ClassNotFoundException(name)
    }
  }

  def allClasses = classes.toMap


}
