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
        div(
          cls := "map-responsive shadow",
          iframe(
            src := "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2030.694763406302!2d17.94730971607906!3d59.404804881685365!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x465f9eed3364c17f%3A0x7e32b513440b6999!2sElectrum!5e0!3m2!1sen!2sse!4v1581004222915!5m2!1sen!2sse",
            width := "600",
            height := "450",
            attr("frameborder") := "0",
            style := "border:0;",
            attr("allowfullscreen") := ""
          )
        )
        // img(BootstrapStyle.shadowSmall,
        //     BootstrapStyle.imgFluid,
        //     src := "https://discan18.github.io/assets/images/direction.png",
        //     alt := "Map of Electrum in Kista")
      )
    );
}
