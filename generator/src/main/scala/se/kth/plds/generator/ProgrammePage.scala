package se.kth.plds.generator

import scalatags.Text.all._
import java.util.{Calendar, Date}

object ProgrammePage extends Page {
  val title = "Programme";
  val file = "programme.html";
}

class ProgrammePage(val talks: List[TalkSlot] = Nil) {
  import ProgrammePage._;

  def generate(): String = Frame.embed(page, ProgrammePage);

  lazy val (dayOneTalks, dayTwoTalks) = talks.partition(talk => {
    val date = talk.startTime;
    val cal = Calendar.getInstance();
    cal.setTime(date);
    val day = cal.get(Calendar.DAY_OF_MONTH);
    day == 5
  });

  lazy val page =
    div(
      BootstrapStyle.container,
      h2(title),
      h5(BootstrapStyle.display5, "Day 1", span(BootstrapStyle.textMuted, " | Thursday 5 March")),
      programmeListing(dayOneTalks, "day1"),
      hr,
      h5(BootstrapStyle.display5, "Day 2", span(BootstrapStyle.textMuted, " | Friday 6 March")),
      programmeListing(dayTwoTalks, "day2")
    );

// <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
//           Collapsible Group Item #1
//         </button>

  private def programmeListing(dayTalks: List[TalkSlot], idExtension: String): Tag =
    div(
      BootstrapStyle.accordion,
      id := s"programme-listing-${idExtension}",
      for ((talk, talkIndex) <- dayTalks.zipWithIndex)
        yield div(
          BootstrapStyle.card,
          div(
            BootstrapStyle.cardHeader,
            StandardStyle.smallerPaddingHeader,
            id := s"heading-${idExtension}-talk${talkIndex}",
            h6(span(BootstrapStyle.fontItalic, talk.startTimeOnly, span(raw(" - ")), talk.endTimeOnly),
               span(raw("&nbsp;&nbsp;&nbsp;")),
               talk.talk.title),
            span(talk.talk.speaker),
            span(raw(" ")),
            if (!talk.talk.affiliation.isEmpty()) {
              span(BootstrapStyle.textMuted, "(", talk.talk.affiliation, ")")
            },
            if (!talk.talk.`abstract`.isEmpty()) {
              div(
                BootstrapStyle.floatRight,
                BootstrapStyle.textMuted,
                button(
                  BootstrapStyle.btn,
                  BootstrapStyle.btnLink,
                  data("toggle") := "collapse",
                  data("target") := s"#abstract-${idExtension}-talk${talkIndex}",
                  aria.expanded := true,
                  aria.controls := s"abstract-${idExtension}-talk${talkIndex}",
                  "Show/Hide Abstract"
                )
              )
            }
          ),
          if (!talk.talk.`abstract`.isEmpty()) {
            div(
              BootstrapStyle.collapse,
              id := s"abstract-${idExtension}-talk${talkIndex}",
              aria.labelledby := s"heading-${idExtension}-talk${talkIndex}",
              data("parent") := s"#programme-listing-${idExtension}",
              div(BootstrapStyle.cardBody, raw(talk.talk.`abstract`))
            )
          }
        )
    );
}
