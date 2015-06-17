package com.cluda.coinsignals.streams.model

import spray.json.DefaultJsonProtocol

object StreamStatsProtocol extends DefaultJsonProtocol {
  implicit val streamStatsFormat = jsonFormat22(StreamStats)
  implicit val streamPrivateFormat = jsonFormat2(StreamPrivate)
}

case class SStream(
  id: String,
  exchange: String,
  currencyPair: String,
  status: Int,
  idOfLastSignal: Long,
  streamPrivate: StreamPrivate,
  stats: StreamStats = StreamStats(),
  computeComponents: ComputeComponents = ComputeComponents()
  ) {
  def publicJson: String = {
    import StreamStatsProtocol._
    import spray.json._
    val json = """{ "id": """" + id + """",""" +
      """ "exchange": """" + exchange + """",""" +
      """ "currencyPair": """" + currencyPair + """",""" +
      """ "stats": """ + stats.toJson.compactPrint + """}"""
    println(json)
    json
  }

  def privateJson: String = {
    import StreamStatsProtocol._
    import spray.json._
    val json = """{ "id": """" + id + """",""" +
      """ "exchange": """" + exchange + """",""" +
      """ "currencyPair": """" + currencyPair + """",""" +
      """ "status": """" + status + """",""" +
      """ "streamPrivate": """ + streamPrivate.toJson.prettyPrint + """,""" +
      """ "stats": """ + stats.toJson.prettyPrint + """}"""
    println(json)
    json
  }
}

case class StreamPrivate(
  apiKey: String,
  topicArn: String
  )

case class StreamStats(
  timeOfFirstSignal: Long = 0,
  timeOfLastSignal: Long = 0,
  numberOfSignals: Long = 0,
  numberOfClosedTrades: Long = 0,
  numberOfProfitableTrades: Long = 0,
  numberOfLoosingTrades: Long = 0,
  accumulatedProfit: BigDecimal = 0,
  accumulatedLoss: BigDecimal = 0,
  averageTrade: BigDecimal = 0,
  partWinningTrades: BigDecimal = 0,
  partLoosingTrades: BigDecimal = 0,
  profitFactor: BigDecimal = 0,
  buyAndHoldChange: BigDecimal = 0,
  averageWinningTrade: BigDecimal = 0,
  averageLoosingTrade: BigDecimal = 0,
  averageMonthlyProfitIncl: BigDecimal = 0,
  averageMonthlyProfitExcl: BigDecimal = 0,
  monthsOfTrading: BigDecimal = 0,
  maxDrawDown: BigDecimal = 0,
  allTimeValueIncl: BigDecimal = 1,
  allTimeValueExcl: BigDecimal = 1,
  firstPrice: BigDecimal = 0
  )

case class ComputeComponents(
  maxDDPrevMax: BigDecimal = 1,
  maxDDPrevMin: BigDecimal = 1,
  maxDDMax: BigDecimal = 1
  )