package io.github.counter2015.graph

// implement sbt "inspect tree compile:compile"'s graph
object Graph {

  def insertBar(s: String, at: Int): String =
    if (at < s.length)
      s.slice(0, at) +
        (s(at).toString match {
          case " " => "|"
          case x => x
        }) +
        s.slice(at + 1, s.length)
    else s

  def asciiDisplay(root: TreeNode[String]): Seq[String] = {

    def toAsciiLines(node: TreeNode[String], level: Int): (String, Seq[String]) = {
      val twoSpaces = " " + " "
      val line = (twoSpaces * level) + "+-" + node.data
      val cs = node.children
      val childLines = cs map {
        toAsciiLines(_, level + 1)
      }

      val withBar: Seq[String] = childLines.zipWithIndex flatMap {
        case ((line, withBar), pos) if pos < (cs.size - 1) =>
          (line +: withBar) map {
            insertBar(_, 2 * (level + 1))
          }
        case ((line, withBar), _) if withBar.lastOption.getOrElse(line).trim != "" =>
          (line +: withBar) ++ Vector(twoSpaces * (level + 1))
        case ((line, withBar), _) => line +: withBar
      }

      (line, withBar)
    }

    val (line, withBar) = toAsciiLines(root, 0)
    line +: withBar
  }
}



