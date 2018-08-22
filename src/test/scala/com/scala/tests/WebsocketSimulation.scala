package com.scala.tests

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class WebsocketSimulation extends Simulation {

  //val httpConf = http
  //  .wsBaseURL("wss://ws-feed.gdax.com")

  val scn = scenario("WebSocket")
    .exec(ws("Connect WS")
      .open("wss://ws-feed.gdax.com"))
    .pause(1)
    .exec(ws("Close WS").close)

}
