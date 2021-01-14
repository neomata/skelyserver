package com.neomata.pages.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, RemoteAddress}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.neomata.pages.http.html.HtmlCodes
import com.neomata.pages.task.evaluate.SubmissionEvaluator
import com.neomata.pages.task.evaluate.SubmissionEvaluator.SubmissionParameters
import com.neomata.skelyserver.server.SkelyRouter
import com.typesafe.scalalogging.StrictLogging

class Router(host: String, port: Int)(implicit system: ActorSystem[_]) extends SkelyRouter with StrictLogging {
  val submissionEvaluator = new SubmissionEvaluator
  var submissions = 22

  def route(remoteAddress: RemoteAddress): Route = {
    concat(
      pathSingleSlash {
        logger.info("{} is accessing main page", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.mainPage)))
      },
      path("submit") {
        logger.info("{} is accessing submission results", remoteAddress.toIP)
        parameters("q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10") {
          case (q1, q2, q3, q4, q5, q6, q7, q8, q9, q10) =>
            val sp = SubmissionParameters(q1, q2, q3, q4, q5, q6, q7, q8, q9, q10)
            val sr = submissionEvaluator.evaluate(sp)
            submissions = submissions + 1
            logger.info(s"Submissions: $submissions")
            complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.resultsPage(sr))))
        }
      }
    )
  }
}
