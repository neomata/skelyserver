package com.neomata.skelyserver.server.evaluate

object SubmissionEvaluator {
  case class SubmissionParameters(
    q1: String,
    q2: String,
    q3: String,
    q4: String,
    q5: String,
    q6: String,
    q7: String,
    q8: String,
    q9: String,
    q10: String
  )

  case class SubmissionResults(score: Int)
}

class SubmissionEvaluator {
  import SubmissionEvaluator._

  def evaluate(sp: SubmissionParameters): SubmissionResults = {
    var score = 0

    val q1 = sp.q1
  }
}