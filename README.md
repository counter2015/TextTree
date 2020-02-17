这是某群的入群题，[原题地址](https://bitbucket.org/snippets/centaur/qojRG)

## 题目描述
在任意一个 sbt 项目中运行 `sbt "inspect tree compile:compile"` ，
能够看到 sbt 将 compile 这个 `Task` 所依赖的其它 `Setting` 及 `Task` 信息以文本树的形式显示出来。本月的试题就是实现文本树的生成。

定义树形数据结构:
```scala
case class TreeNode[T](data: T, children: Seq[TreeNode[T]] = Nil)
```
请实现方法

```scala
def asciiDisplay(root: TreeNode[String]): Seq[String]
```
将 `root` 的内容显示成文本树。

示例：
```
asciiDisplay(TreeNode("Root",
      children = List(TreeNode("level1-1"),
        TreeNode("level1-2"),
        TreeNode("level1-3")))).foreach(println)
```
的输出： ![图1](https://raw.githubusercontent.com/Centaur/images/master/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-09-11%20%E4%B8%8B%E5%8D%889.38.05.png)

```
asciiDisplay(TreeNode("Root",
  children = List(
    TreeNode("level1-1", children = TreeNode("level2-1", children = TreeNode("level3-1") :: Nil) :: Nil),
    TreeNode("level1-2"),
    TreeNode("level1-3")))).foreach(println)
```
的输出： ![图2](https://github.com/Centaur/images/raw/master/%E5%B1%8F%E5%B9%95%E5%BF%AB%E7%85%A7%202016-09-11%20%E4%B8%8B%E5%8D%889.50.55.png)

更细致的需求请参考 sbt "inspect tree compile:compile" 的输出。

要求：

请在github上（或使用任何一个开放的代码托管服务如bitbucket.org, git.oschina.net, coding.net等）创建完整的sbt项目，使用Scala语言解决本题。将项目链接作为入群问题的答案。
尽量不使用 var
用ScalaTest, Specs2或ScalaCheck编写测试（我们也接受使用JUnit或其它工具编写的测试）。
本群也欢迎猎头及HR人员入群，此类请将公司招聘网页的链接或招聘文档百度网盘链接作为入群问题的答案。

## 我的思路
既然是模拟sbt, 那当然是把源码扒过来看咯。

1. `git clone https://github.com/sbt/sbt.git`
2. IDEA 打开项目， 在项目根目录，用`Ctrl + F`快速搜索定位`+-`所在位置
3. 然后你就能看到相关代码了，其实我觉得github搜索的功能做好点就不要前面那么麻烦的步骤了

具体的位置在此处：
https://github.com/sbt/sbt/blob/540ee83f2ae812e4db3a4c79a4f91a2bdfc08735/main/src/main/scala/sbt/internal/SettingGraph.scala#L78-L116

接下来就是照抄，然后魔改就行了。
