package com.github.krever.jimcy

import java.io.ByteArrayOutputStream
import java.net.URI
import javax.tools.JavaFileObject.Kind
import javax.tools.SimpleJavaFileObject

 protected[jimcy] case class InMemoryJavaClassObject(private val name :String, private val objectKind :Kind)
  extends SimpleJavaFileObject(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE) {

  private val byteOutputStream = new ByteArrayOutputStream()

  def bytes = byteOutputStream.toByteArray

  override def openOutputStream = byteOutputStream

}
