package io.github.counter2015.graph

case class TreeNode[T](data: T, children: Seq[TreeNode[T]] = Nil)
