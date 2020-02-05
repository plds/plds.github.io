package se.kth.plds.generator

import scalatags.Text.all._

object VenuePage extends Page {
  val title = "Venue";
  val file = "venue.html";
}

class VenuePage(val talks: List[Talk] = Nil) {
  import VenuePage._;

  def generate(): String = Frame.embed(page, VenuePage);

  lazy val page =
    div(
      BootstrapStyle.container,
      h2(title),
      div(h5("Location"),
          p(
            "RISE Computer Science: Isafjordsgatan 22, Kista, Sweden, Floor: 6, Elevator: B (Reception) , Main Room: Knuth"
          )),
      div(
        h5("Directions"),
        p(
          """
            | RISE SICS is located at the Electrum building which is within a short walking distance (5min) 
            | from the Kista metro station (blue line to Akalla) and the Kista Galleria shopping mall. 
            | Arlanda (ARN) airport is around 25min away by taxi.
            """.stripMargin
        )
      ),
      div(
        BootstrapStyle.container,
        img(BootstrapStyle.shadowSmall,
            BootstrapStyle.imgFluid,
            src := "https://discan18.github.io/assets/images/direction.png",
            alt := "Map of Electrum in Kista")
      )
    );
}
