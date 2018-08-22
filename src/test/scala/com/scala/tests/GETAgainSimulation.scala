package com.scala.tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder.toActionBuilder


class GETAgainSimulation extends Simulation {
  val theHttpProtocolBuilder = http
    .baseURL("http://computer-database.gatling.io")

  val theScenarioBuilder = scenario("Scenario1")
    .exec(
      http("Bad Request")
        .get("/unknown")
        .check(
          status.is(404),
          regex("Action not found").count.is(2)
        )
    )

  setUp(
    theScenarioBuilder.inject(atOnceUsers(1))
  )
    .assertions(
      global.responseTime.max.lte(500),
      forAll.failedRequests.count.lte(5),
      details("Bad Request").successfulRequests.percent.gte(90)
    )
    .protocols(theHttpProtocolBuilder)
}