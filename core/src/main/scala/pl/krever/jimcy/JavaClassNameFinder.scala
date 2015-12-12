package pl.krever.jimcy

import java.util.regex.Pattern

/**
  * Created by krever on 12/12/15.
  */
object JavaClassNameFinder {

  val pattern = Pattern.compile("""\s*(public|private)\s+class\s+(\w+)\s+((extends\s+\w+)|(implements\s+\w+( ,\w+)*))?\s*\{""")

  def findName(classSource: String): String = {
    val matcher = pattern.matcher(classSource)
    val result = if (matcher.find)
      matcher.group(2)
    else
      throw new RuntimeException("Class name not found")
    result
  }


}
