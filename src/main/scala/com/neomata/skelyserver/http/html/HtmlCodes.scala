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
}