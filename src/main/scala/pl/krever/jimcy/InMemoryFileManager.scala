package pl.krever.jimcy

import javax.tools.JavaFileManager.Location
import javax.tools.JavaFileObject.Kind
import javax.tools._

import scala.collection.mutable


protected[jimcy] class InMemoryFileManager(standardManager: JavaFileManager) extends ForwardingJavaFileManager[JavaFileManager](standardManager) {

  private val classes: mutable.Map[String, InMemoryJavaClassObject] = mutable.Map()

  val classLoader = new InMemoryClassLoader(classes)

  override def getClassLoader(location: Location): ClassLoader = classLoader

  override def getJavaFileForOutput(location: Location, className: String,
                                    kind: Kind, sibling: FileObject): JavaFileObject = {
    val classObject = InMemoryJavaClassObject(className, kind)
    classes.put(className, classObject)
    classObject
  }



}


