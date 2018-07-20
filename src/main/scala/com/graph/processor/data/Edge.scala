package com.graph.processor.data

class Edge {

  var from: Node = _
  var to: Node = _
  var amount: Double = _

  def this(from: Node, to: Node, amount: Double) {
    this()
    this.from = from
    this.to = to
    this.amount = amount
  }

  def getString: String = {
    s"Transfer from ${from.accountId},to ${to.accountId},amount=${amount}\n${to.getString}"
  }

}
