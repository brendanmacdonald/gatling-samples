package com.scala.tests

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class GETSimulation extends Simulation {

  private val httpConfig = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("application/json; charset=utf-8")

  val scn = scenario("GetSimulation")
    .exec(http("Get test")
      .get("/computers")
      .check(status.is(200))
      .check(GETSimulation.pageHeader.exists))
    .pause(1)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConfig)

  object GETSimulation {
    def pageHeader = css("h1")
  }

}
