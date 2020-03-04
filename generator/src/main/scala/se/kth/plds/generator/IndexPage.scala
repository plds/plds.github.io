package se.kth.plds.generator

import scalatags.Text.all._
import java.util.Calendar

object IndexPage extends Page {

  val title = "Home";
  val file = "index.html";
}
class IndexPage {
  import IndexPage._;

  def generate(): String = Frame.embed(page, IndexPage);

  lazy val page =
    div(
      BootstrapStyle.container,
      div(BootstrapStyle.row, left, right)
    );

  lazy val left = div(
    BootstrapStyle.colSmall(7),
    p(
      BootstrapStyle.lead,
      BootstrapStyle.textJustify,
      "A closed workshop event that brings together fundamental research talks and discussions within the intersection of programming languages and (distributed) systems."
    ),
    p(
      BootstrapStyle.textJustify,
      "Day 1 (March 5th) will consist of an afternoon session between 13:00 and 18:00, and Day 2 (March 6th) will feature a morning session from 09:00 to 12:00."
    ),
    h5("Note"),
    p(
      BootstrapStyle.textJustify,
      "The workshop is collocated with the public Ph.D. defence of Lars Kroll, which will take place at KTH Electrum in the afternoon of March 6th, beginning at 13:00."
    ),
    h4("Contact"),
    p(
      //BootstrapStyle.textJustify,
      "If you have any problems, inquiries or questions, please feel free to send an e-mail to ",
      a(BootstrapStyle.textNoWrap,
        href := "mailto:lkroll@kth.se",
        i(cls := "fa fa-envelope-o fa-fw", aria.hidden := true),
        "lkroll@kth.se")
    )
  );
  lazy val right = div(
    BootstrapStyle.colSmall(0),
    div(
      BootstrapStyle.card,
      BootstrapStyle.shadow,
      div(BootstrapStyle.cardHeader,
          cls := "py-1",
          BootstrapStyle.bgDark,
          span(BootstrapStyle.textLight, cls := "font-weight-bold", "Updates")),
      ul(
        BootstrapStyle.listGroup,
        BootstrapStyle.listGroupFlush,
        for (item <- news) yield {
          li(BootstrapStyle.listGroupItem,
             label(BootstrapStyle.label, BootstrapStyle.textMuted, item.date),
             span(raw(" – ")),
             span(item.content))
        }
      )
    )
    // h4("Updates"),
    // p("Test")
  );

  val news: List[NewsItem] = List(
    NewsItem("4 March, 2020", "Final programme published"),
    NewsItem("27 February, 2020", "Preliminary programme published"),
    NewsItem("5 February, 2020", "Website published")
  );
}

case class NewsItem(date: String, content: String)
