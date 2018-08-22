package com.scala.tests

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class POSTSimulation extends Simulation {

  before {
    println("Simulation is about to start!")
  }

  after {
    println("Simulation is finished!")
  }

  val headers = Map("Content-Type" -> "application/x-www-form-urlencoded")
  val config = http
    .baseURL("http://computer-database.gatling.io")
    .acceptHeader("application/json; charset=utf-8")

  object Post {
    val makePost = {
      exec(http("Post to a url")
        .post("/computers")
        .headers(headers)
        .formParam("name", "Beautiful Computer")
        .formParam("introduced", "2012-05-30")
        .formParam("discontinued", "")
        .formParam("company", "37")
        .check(status.is(200)))
        .pause(1)
    }
  }

  private val scn = scenario("Make a POST").exec(Post.makePost)

  setUp(scn.inject(rampUsers(5) over (10 seconds)))
    .protocols(config)
    .assertions(
      global.responseTime.max.lt(1000),
      global.failedRequests.count.is(0),
      global.successfulRequests.percent.gt(99))
}
