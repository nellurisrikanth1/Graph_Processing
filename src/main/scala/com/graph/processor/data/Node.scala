package com.graph.processor.data

class Node {

  var accountId: String = _
  var connectedEdges: Array[Edge] = _

  def this(accId: String, edges: Array[Edge]) {
    this()
    this.accountId = accId
    this.connectedEdges = edges
  }

  def getString: String = {
    if (Option(connectedEdges).isDefined && connectedEdges.filter(_.from.accountId == accountId).nonEmpty)
      connectedEdges.filter(_.from.accountId == accountId).map(_.getString).mkString("\n")
    else
      ""
  }

}

