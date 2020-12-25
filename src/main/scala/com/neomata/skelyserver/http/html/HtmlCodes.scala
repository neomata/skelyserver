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
       |                <p><input type="radio" name="question1" value="Stall">Give them what they ask for; my friend is probably terrified.</p>
       |            </div>
       |            <div>
       |                <p>2. If a baby is power level: 1 and the strongest person is power level: 100, my power level is: </p>
       |                <input type="number" name="question2" value="1">
       |            </div>
       |            <div>
       |                <p>3. W-A-V-Y-: </p>
       |                <p><input type="radio" name="question1" value="Stall">Stall them until help arrives; hopefully a professional will be able to consult better than I.</p>
       |                <p><input type="radio" name="question1" value="Refuse">Refuse their demands; why should a criminal win?</p>
       |                <p><input type="radio" name="question1" value="Stall">Give them what they ask for; my friend is probably terrified.</p>
       |            </div>
       |        </form>
       |    </body>
       |</html>""".stripMargin
  }
}