package se.kth.plds.generator

import scalatags.Text.all._
import java.util.Date

case class Talk(title: String, `abstract`: String, time: Date)

object ProgrammePage extends Page {
  val title = "Programme";
  val file = "programme.html";
}

class ProgrammePage(val talks: List[Talk] = Nil) {
  import ProgrammePage._;

  def generate(): String = Frame.embed(page, ProgrammePage);

  lazy val page =
    div(
      BootstrapStyle.container,
      h2(title),
      p(
        i(
          "The programme will be published after the registration deadline."
        )
      )
    );
}
