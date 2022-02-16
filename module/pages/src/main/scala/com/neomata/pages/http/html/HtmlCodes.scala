package com.neomata.pages.http.html

import com.neomata.pages.task.evaluate.SubmissionEvaluator.SubmissionResults

object HtmlCodes {
  def cameraHtmlScript(host: String, port: Int, num: Int): String = {
    s"""<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>camera</title>
       |    </head>
       |    <body id="body" style="margin: auto; background: #0e0e0e;">
       |        <script>
       |            function loader() {
       |                var image = document.getElementById("img");
       |                image.src = "http://$host:$port/snapshot?device=$num&" + new Date().getTime();
       |            };
       |        </script>
       |        <img id="img" onload="loader()" style="-webkit-user-select: none;margin: auto;cursor: zoom-in;" src="http://$host:$port/snapshot?device=$num" alt="Image from web camera">
       |    </body>
       |</html>""".stripMargin
  }

  def mainPage: String = {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>Decimoto</title>
       |    </head>
       |    <body>
       |        <form action="/submit">
       |            <div>
       |                <h1>Decimoto</h1>
       |                <h2>Power Level Evaluator</h2>
       |            </div>
       |            <div>
       |                <h4>1. You're on the phone with a criminal who holds your friend hostage and will kill them in 10 minutes, you will: </h4>
       |                <p><input type="radio" name="q1" value="Stall">Stall them until help arrives; I'm not qualified enough for this.</p>
       |                <p><input type="radio" name="q1" value="Refuse">Refuse their demands; negotiate a different deal</p>
       |                <p><input type="radio" name="q1" value="Comply">Give them what they ask for; my friend's safety is most important</p>
       |                <p><input type="radio" name="q1" value="Storm">Storm the building guns-blazing. You kill criminal, but also your friend in the process</p>
       |            </div>
       |            <div>
       |                <h4>2. Males: If a baby is power level: 1 and the strongest person is power level: 100, my power level is: <br>Females: If the ugliest person is beauty level: 1 and the most beautiful person is beauty level: 100, my beauty level is: </h4>
       |                <input type="number" name="q2" value="1">
       |            </div>
       |            <div>
       |                <h4>3. W-A-V-Y-: Find the next letter in the pattern. </h4>
       |                <p><input type="radio" name="q3" value="S">S</p>
       |                <p><input type="radio" name="q3" value="H">H</p>
       |                <p><input type="radio" name="q3" value="U">U</p>
       |                <p><input type="radio" name="q3" value="T">T</p>
       |                <p><input type="radio" name="q3" value="X">X</p>
       |            </div>
       |            <div>
       |                <h4>4. Fastest mile-run time (MM:SS): </h4>
       |                <input type="text" name="q4" id="q4">
       |            </div>
       |            <div>
       |                <h4>5. Maximum consecutive push-ups: </h4>
       |                <input type="number" name="q5" id="q5">
       |            </div>
       |            <div>
       |                <h4>6. Fastest 400 meter run time as a whole number in seconds: </h4>
       |                <input type="number" name="q6">
       |            </div>
       |            <div>
       |                <h4>7. You need to train to become the incumbent fusion of treacherous tranquility and virtuous violence: </h4>
       |                <p><input type="radio" name="q7" value="Hyper">Train every moment until I feel my life on the brink... I will achieve greatness</p>
       |                <p><input type="radio" name="q7" value="Aware">Constantly push myself hard to reach my limit. I should...no I must find my potential</p>
       |                <p><input type="radio" name="q7" value="Intelligent">I will scour the ends of the earth for all knowledge. That will bring me beyond that of all beings</p>
       |                <p><input type="radio" name="q7" value="Optimistic">I will find true purpose. I know that sacrifices are necessary to reach the ultimate destiny</p>
       |            </div>
       |            <div>
       |                <h4>8. Mob Boss over the phone: So you da one I been hearin\' about...da one causing me all this trouble. I lost 3 million because of you...you killed my buddy Robby...with a icepick for God sake. You're gonna pay for what you've done..ya hear me? </h4>
       |                <p><input type="radio" name="q8" value="Intimidation">Do what you gotta do but if you come after me, dont't fail, cause I won't when I'm comin' for you... </p>
       |                <p><input type="radio" name="q8" value="Logos">I couldn't be the killer, the details just don't add up. You're a smart guy; isn't it obvious one of your men did it, Joey you know the way he felt about Robby</p>
       |                <p><input type="radio" name="q8" value="Pathos">I know you loved Robby, and I'm sorry that he went that way...but it wasn't me I have a family...kids. Its them who I care about.</p>
       |                <p><input type="radio" name="q8" value="Ethos">Me and you..we're the same. Two people with a lot of money and even more responsibility. I've been in the game for too long; I know not to cross that line.</p>
       |            </div>
       |            <div>
       |                <h4>9. Hold your breath, how long in whole seconds?: </h4>
       |                <input type="number" name="q9">
       |            </div>
       |            <h4>10. This question measures your ability to make quick decisions. You get 45 seconds to read the question and answer. The quiz will be autosubmitted after the timer so do this question last.</h4>
       |            <input type="button" name="toggler" value="Open" onclick="toggleQuestion10()">
       |            <div id="toggle" style="color:white">
       |                <h5>You are exploring through a muddy, malevolent, monster-ridden dungeon to loot for treasure that will improve your gear. You collapse and fall through an time-battered bridge into a dark pit. You hear distant monster snarls getting louder and louder with each passing second. Your're heart is beating a maximum frequency and are petrified with a sensation of impending doom. Moments later you find yourself surrounded by a group of monsters, who will soon find you and eat you alive, what should I do?: </h4>
       |                <p><input type="radio" name="q10" value="Time">Throw your only knife at the pit wall to distract the monsters and buy you more time.</p>
       |                <p><input type="radio" name="q10" value="Torch">Take your lighter and ignite a wooden stick next to you to scare off the dungeon monsters with fire</p>
       |                <p><input type="radio" name="q10" value="Offense">Bee-line for the exit and use knife for any monster in my way</p>
       |                <p><input type="radio" name="q10" value="Sneak">Drop all my loot :( and take off gear so I can be as quiet and light as possible to sneak out</p>
       |                <p><input type="radio" name="q10" value="Smart">Cover yourself in mud to hide your scent and to mask your smell from the monsters</p>
       |            </div>
       |            <div>
       |                <input type="submit" id="submit" value="Submit">
       |            </div>
       |        </form>
       |        <script>
       |            function toggleQuestion10() {
       |                var q10 = document.getElementById("toggle");
       |                q10.style.color = "black";
       |                setInterval(function immediate() {
       |                    var submission = document.getElementById("submit");
       |                    submission.click();
       |                }, 45000);
       |            }
       |        </script>
       |    </body>
       |</html>""".stripMargin
  }

  def resultsPage(sr: SubmissionResults): String = {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>Results</title>
       |    </head>
       |    <body>
       |      <h1>Results</h1>
       |      <br>
       |      <h2>Level: ${sr.level}</h2>
       |      <h3>${sr.score} / 1000</h3>
       |      <br>
       |      <a href="http://decimoto.com/">
       |          <button>Retry</button>
       |      </a>
       |    </body>
       |</html>""".stripMargin
  }

  def heartCrest: String = {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>HeartCrest</title>
       |    </head>
       |    <body id="body" style="margin: auto">
       |      <h1>Happy Valentine's Day</h1>
       |      <br>
       |      <h2>Answer these trivia questions to get prizes!<h2>
       |      <br>
       |      <a href="http://192.168.0.119:80/question1">
       |        <button>Next</button>
       |      </a>
       |    </body>
       |</html>""".stripMargin
  }

  def heartQuestion1: String =  {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>HeartCrest</title>
       |    </head>
       |    <body id="body" style="margin: auto">
       |      <form action="/submit1">
       |         <h4>1. Bishop loves you 1,000,000 out of: </h4>
       |                <p><input type="radio" name="q" value="1">100</p>
       |                <p><input type="radio" name="q" value="1">1,000,000</p>
       |                <p><input type="radio" name="q" value="1">1</p>
       |                <p><input type="radio" name="q" value="1">1,000</p>
       |         <div>
       |           <input type="submit" id="submit" value="Submit">
       |         </div>
       |      </form>
       |      <br>
       |    </body>
       |</html>""".stripMargin
  }

  def heartQuestion2: String =  {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>HeartCrest</title>
       |    </head>
       |    <body id="body" style="margin: auto">
       |      <form action="/submit1">
       |         <h4>2. Where should we vacation next? </h4>
       |           <p><input type="radio" name="q" value="2">Antarctica</p>
       |           <p><input type="radio" name="q" value="2">Russia</p>
       |           <p><input type="radio" name="q" value="2">Mexico</p>
       |           <p><input type="radio" name="q" value="2">Bahamas</p>
       |         <div>
       |           <input type="submit" id="submit" value="Submit">
       |         </div>
       |      </form>
       |      <br>
       |    </body>
       |</html>""".stripMargin
  }

  def heartQuestion3: String =  {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>HeartCrest</title>
       |    </head>
       |    <body id="body" style="margin: auto">
       |      <form action="/submit1">
       |         <h4>3. :D </h4>
       |                <p><input type="radio" name="q" value="3">:)</p>
       |                <p><input type="radio" name="q" value="3">:P</p>
       |                <p><input type="radio" name="q" value="3">:D</p>
       |                <p><input type="radio" name="q" value="3">:O</p>
       |         <div>
       |           <input type="submit" id="submit" value="Submit">
       |         </div>
       |      </form>
       |      <br>
       |    </body>
       |</html>""".stripMargin
  }

  def heartQuestion4: String =  {
    s"""<!DOCTYPE html>
       |<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>HeartCrest</title>
       |    </head>
       |    <body id="body" style="margin: auto">
       |      <form action="/submit1">
       |         <h4>4. Who is your favorite living being here? </h4>
       |           <p><input type="radio" name="q" value="4">Myself</p>
       |           <p><input type="radio" name="q" value="4">My husband</p>
       |           <p><input type="radio" name="q" value="4">My puppy</p>
       |           <p><input type="radio" name="q" value="4">All of the above</p>
       |
       |        <div>
       |          <input type="submit" id="submit" value="Submit">
       |        </div></form>
       |      <br>
       |    </body>
       |</html>""".stripMargin
  }

  def heartAnswerTemplate(num: Int): String = {
    val message = num match {
      case 1 => "Correct, you will find your prize where you study and (sometimes) eat."
      case 2 => "Correct, you will find your prize where you put things to get cold."
      case 3 => "Correct, you will find your prize where your mom and dad's picture is."
      case 4 => "Correct, you will find your prize with your husband!"
      case 5 => "Happy Valentine's, will you be mine?"
    }

    val next = num match {
      case 1 => 2
      case 2 => 3
      case 3 => 4
      case 4 => 5
      case 5 => 1
    }

    val release =
      s"""<!DOCTYPE html>
        |<html lang="en">
        |    <head>
        |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
        |        <title>HeartCrest</title>
        |    </head>
        |    <body id="body" style="margin: auto">
        |      <h3>$message</h3>
        |      <br>
        |      <a href="http://192.168.0.119:80/question$next">
        |        <button>Next</button>
        |      </a>
        |    </body>
        |</html>""".stripMargin

    release
  }
}