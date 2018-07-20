package com.graph.processor

import com.graph.processor.data.{Edge, Node}

import scala.io.Source

object Enquiry {

  def main(args: Array[String]): Unit = {

    val inpLines = Source.fromResource("transfers.txt").getLines.toArray
    val noOfAccs = inpLines(0)
    val noOfTxs = inpLines(1)
    var nodes: Array[Node] = Array()

    var edges = inpLines.drop(2).map(l => {
      val inp = l.split(" ")
      if (nodes.exists(_.accountId == inp(0)) && nodes.exists(_.accountId == inp(1))) {
        val fromNode = nodes.find(_.accountId == inp(0)).get
        val toNode = nodes.find(_.accountId == inp(1)).get
        val edge = new Edge(fromNode, toNode, inp(2).toDouble)
        fromNode.connectedEdges = fromNode.connectedEdges :+ edge
        toNode.connectedEdges = toNode.connectedEdges :+ edge
        edge
      } else if (nodes.exists(_.accountId == inp(0)) && !nodes.exists(_.accountId == inp(1))) {
        val fromNode = nodes.find(_.accountId == inp(0)).get
        val toNode = new Node(inp(1), Array())
        nodes = nodes :+ toNode
        val edge = new Edge(fromNode, toNode, inp(2).toDouble)
        fromNode.connectedEdges = fromNode.connectedEdges :+ edge
        toNode.connectedEdges = toNode.connectedEdges :+ edge
        edge
      } else if (!nodes.exists(_.accountId == inp(0)) && nodes.exists(_.accountId == inp(1))) {
        val fromNode = new Node(inp(0), Array())
        nodes = nodes :+ fromNode
        val toNode = nodes.find(_.accountId == inp(1)).get
        val edge = new Edge(fromNode, toNode, inp(2).toDouble)
        fromNode.connectedEdges = fromNode.connectedEdges :+ edge
        toNode.connectedEdges = toNode.connectedEdges :+ edge
        edge
      } else {
        val fromNode = new Node(inp(0), Array())
        val toNode = new Node(inp(1), Array())
        nodes = nodes :+ fromNode :+ toNode
        val edge = new Edge(fromNode, toNode, inp(2).toDouble)
        fromNode.connectedEdges = fromNode.connectedEdges :+ edge
        toNode.connectedEdges = toNode.connectedEdges :+ edge
        edge
      }
    })

    println(nodes.find(_.accountId == args(0)).get.getString)

  }

}
