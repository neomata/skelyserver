package com.neomata.heartcrest.server

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse, RemoteAddress}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.neomata.pages.http.html.HtmlCodes
import com.neomata.pages.server.SkelyRouter
import com.neomata.pages.task.evaluate.SubmissionEvaluator
import com.neomata.pages.task.evaluate.SubmissionEvaluator.SubmissionParameters
//import com.neomata.skelyserver.server.SkelyRouter
import com.typesafe.scalalogging.StrictLogging

class Router(host: String, port: Int)(implicit system: ActorSystem[_]) extends SkelyRouter with StrictLogging {
  val submissionEvaluator = new SubmissionEvaluator
  var submissions = 22

  def route(remoteAddress: RemoteAddress): Route = {
    concat(
      pathSingleSlash {
        logger.info("{} is accessing main page", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartCrest)))
      },
      path("question1") {
        logger.info("{} is accessing question 1", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartQuestion1)))
      },
      path("question2") {
        logger.info("{} is accessing question 1", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartQuestion2)))
      },
      path("question3") {
        logger.info("{} is accessing question 1", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartQuestion3)))
      },
      path("question4") {
        logger.info("{} is accessing question 1", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartQuestion4)))
      },
      path("question5") {
        logger.info("{} is accessing question 1", remoteAddress.toIP)
        complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartAnswerTemplate(5))))
      },
      path("submit1") {
        logger.info("{} is accessing answer 1", remoteAddress.toIP)
        parameters("q") { q =>
          complete(HttpResponse(entity = HttpEntity(ContentTypes.`text/html(UTF-8)`, HtmlCodes.heartAnswerTemplate(q.toInt))))
        }
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
