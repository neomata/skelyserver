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
    s"""<html lang="en">
       |    <head>
       |        <meta name="viewport" content="width=device-width, minimum-scale=0.1">
       |        <title>Decimoto</title>
       |    </head>
       |    <body>
       |        <form>
       |            <div>
       |                <p>1. You're on the phone with a criminal who holds your friend hostage and will kill them in 10 minutes, you will: </p>
       |                <p><input type="radio" name="question1" value="Stall">Stall them until help arrives; hopefully a professional will be able to consult better than I.</p>
       |                <p><input type="radio" name="question1" value="Refuse">Refuse their demands; why should a criminal win?</p>
       |                <p><input type="radio" name="question1" value="Comply">Give them what they ask for; my friend is probably terrified.</p>
       |            </div>
       |            <div>
       |                <p>2. If a baby is power level: 1 and the strongest person is power level: 100, my power level is: </p>
       |                <input type="number" name="question2" value="1">
       |            </div>
       |            <div>
       |                <p>3. W-A-V-Y-: </p>
       |                <p><input type="checkbox" name="question3" value="S">S</p>
       |                <p><input type="checkbox" name="question3" value="H">H</p>
       |                <p><input type="checkbox" name="question3" value="U">U</p>
       |                <p><input type="checkbox" name="question3" value="T">T</p>
       |                <p><input type="checkbox" name="question3" value="X">X</p>
       |            </div>
       |            <div>
       |                <p>4. Average mile time: </p>
       |                <input type="time" name="question4" id="q4">
       |            </div>
       |            <div>
       |                <p>5. Maximum consecutive pushups: </p>
       |                <input type="number" name="question5" id="q5">
       |            </div>
       |            <div>
       |                <p>6. Estimate 100m sprint: </p>
       |                <input type="time" name="question6" id="q6">
       |            </div>
       |            <div>
       |                <p>7. You need to train to become the ultimate incumbent fusion of serenity and fury: </p>
       |                <p><input type="radio" name="question7" value="Stall">Stall them until help arrives; hopefully a professional will be able to consult better than I.</p>
       |                <p><input type="radio" name="question7" value="Refuse">Refuse their demands; why should a criminal win?</p>
       |                <p><input type="radio" name="question7" value="Comply">Give them what
       |            </div>
       |            <div>
       |                <p>8. "Mafia Boss over the phone: So you da one I been hearin about...da one causing me all this trouble. I lost $3 million because of you...you killed my buddy Robby...with carbon monoxide for God sake. You're gonna pay for what you've done you hear me?" </p>
       |                <p><input type="radio" name="question7" value="Logos">How could I have been the one to kill him, I have black hair and I'm lightskinned. Doesn't fit the profile. </p>
       |                <p><input type="radio" name="question7" value="Pathos">Please... no. I swear I wasn't the one please!</p>
       |                <p><input type="radio" name="question7" value="Ethos">Hm. Do what you you gotta do, but if you come after me I'm coming after you and I won't fail.</p>
       |            </div>
       |        </form>
       |    </body>
       |</html>""".stripMargin
  }
}