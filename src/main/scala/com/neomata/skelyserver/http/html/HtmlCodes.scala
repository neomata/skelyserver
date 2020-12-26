package com.neomata.skelyserver.http.html

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
       |        <form>
       |            <div>
       |                <h4>1. You're on the phone with a criminal who holds your friend hostage and will kill them in 10 minutes, you will: </h4>
       |                <p><input type="radio" name="q1" value="Stall">Stall them until help arrives; hopefully a professional will be able to consult better than I.</p>
       |                <p><input type="radio" name="q1" value="Refuse">Refuse their demands; why should a criminal win?</p>
       |                <p><input type="radio" name="q1" value="Comply">Give them what they ask for; my friend is probably terrified.</p>
       |            </div>
       |            <div>
       |                <h4>2. If a baby is power level: 1 and the strongest person is power level: 100, my power level is: </h4>
       |                <input type="number" name="q2" value="1">
       |            </div>
       |            <div>
       |                <h4>3. W-A-V-Y-: </h4>
       |                <p><input type="checkbox" name="q3" value="S">S</p>
       |                <p><input type="checkbox" name="q3" value="H">H</p>
       |                <p><input type="checkbox" name="q3" value="U">U</p>
       |                <p><input type="checkbox" name="q3" value="T">T</p>
       |                <p><input type="checkbox" name="q3" value="X">X</p>
       |            </div>
       |            <div>
       |                <h4>4. Average mile time: </h4>
       |                <input type="time" name="q4" id="q4">
       |            </div>
       |            <div>
       |                <h4>5. Maximum consecutive pushups: </h4>
       |                <input type="number" name="q5" id="q5">
       |            </div>
       |            <div>
       |                <h4>6. Estimate 100m sprint: </h4>
       |                <input type="time" name="q6" id="q6">
       |            </div>
       |            <div>
       |                <h4>7. You need to train to become the incumbent fusion of treacherous serenity and virtuous fury: </h4>
       |                <p><input type="radio" name="q7" value="Hyper">Train every moment until I feel my life slipping away, I will achieve greatness</p>
       |                <p><input type="radio" name="q7" value="Balance">Constantly push myself hard to reach my limit. I should...no I must find my potential</p>
       |                <p><input type="radio" name="q7" value="Intel">I will seek a master to teach me. I need to be as efficient in each training as possible</p>
       |                <p><input type="radio" name="q7" value="Optimistic">I will find true absolution. I know that if I train properly I will reach the ultimate destiny</p>
       |            </div>
       |            <div>
       |                <h4>8. Mafia Boss over the phone: So you da one I been hearin\' about...da one causing me all this trouble. I lost 3 million because of you...you killed my buddy Robby...with a icepick for God sake. You're gonna pay for what you've done..ya hear me? </h4>
       |                <p><input type="radio" name="q8" value="Intimidation">You dare accuse me? I will break every bone in your body and drill holes in your skull.</p>
       |                <p><input type="radio" name="q8" value="Logos">How could I have been the one to kill him, I have black hair and I'm lightskinned. Doesn't fit the profile. </p>
       |                <p><input type="radio" name="q8" value="Pathos">Please... no. I swear I wasn't the one please! I'll do anything!</p>
       |                <p><input type="radio" name="q8" value="Ethos">Me and you..we're the same. Two people with a lot of money and even more responsibility. I don't know who you think I am, but if you come after me I'll have no choice but to reciprocate</p>
       |            </div>
       |            <div>
       |                <h4>9. Hold your breath, how long?: </h4>
       |                <p><input type="time" name="q9 id="q9"</p>
       |            </div>
       |
       |            <h4>10. This question measures your ability to make quick decisions. You get 15 seconds to read the question and answer. The quiz will be autosubmitted after the timer so do this question last.</h4>
       |            <input type="button" name="toggler" value="Toggle" onclick="toggleQuestion10()"></button>
       |            <div id="toggle" style="color:white">
       |                <h5>You are running through a muddy dungeon to looking for a way to escape after looting an perfect gear for your new armor set. It is SO PERFECT that it makes EVERYTHING perfect. You find an exit door down the hall that is slowly shutting. You make a break for the exit door before it shuts. A massive stalagtite falls from the ceiling and pins the aglet of your boot to the ground. Dust gets in your eyes and you can't see. You don't have enough energy to hold the perfect gear and yank your aglet out of the stalagtite: </h4>
       |                <p><input type="radio" name="q10" value="Safety">Reach for your knife and cut the shoe string, but risk dropping the perfect gear in the cavern below.</p>
       |                <p><input type="radio" name="q10" value="One Shoe">Slide your foot out of your boot and rush to the exit</p>
       |                <p><input type="radio" name="q10" value="Lucky">Throw the perfect gear out of your possession and get your boot free, make a break for the exit. </p>
       |                <p><input type="radio" name="q10" value="Half">Drop some of the pieces from the perfect gear making it an almost-perfect gear so you will be able to hold it and yank your foot.</p>
       |            </div>
       |            <div>
       |                <input type="submit" value="Submit">
       |            </div>
       |        </form>
       |        <script>
       |            function toggleQuestion10() {
       |                var q10 = document.getElementById("toggle");
       |                if (q10.style.color === "white") {
       |                    q10.style.color = "black";
       |                } else {
       |                    q10.style.color = "white";
       |                }
       |            }
       |        </script>
       |    </body>
       |</html>""".stripMargin
  }
}