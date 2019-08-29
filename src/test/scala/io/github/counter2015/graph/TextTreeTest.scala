package io.github.counter2015.graph

import io.github.counter2015.graph.Graph.asciiDisplay
import org.scalatest.{FunSuite, Matchers}

class TextTreeTest extends FunSuite with Matchers {


  test("test0: sample input") {

    val text = asciiDisplay(TreeNode("Root",
      children = List(TreeNode("level1-1"), TreeNode("level1-2"), TreeNode("level1-3"))))

    val res = Seq(
      "+-Root",
      "  +-level1-1",
      "  +-level1-2",
      "  +-level1-3",
      "  ")

    text should be(res)
  }

  test("test1: sample input") {

    val text = asciiDisplay(TreeNode("Root",
      children = List(
        TreeNode("level1-1", children = TreeNode("level2-1", children = TreeNode("level3-1") :: Nil) :: Nil),
        TreeNode("level1-2"),
        TreeNode("level1-3"))))

    val res = Seq(
      "+-Root",
      "  +-level1-1",
      "  | +-level2-1",
      "  |   +-level3-1",
      "  |   ",
      "  +-level1-2",
      "  +-level1-3",
      "  ")

    text should be(res)
  }


  test("test2: one line") {
    val text = asciiDisplay(TreeNode("Root"))
    assert(text.mkString("\n") == "+-Root")
  }
}
