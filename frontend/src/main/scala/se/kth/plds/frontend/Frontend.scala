package se.kth.plds.frontend

import scala.scalajs.js.annotation._
import scalajs.js, js.UndefOr
import org.scalajs.dom
import scalatags.JsDom.all._
import org.scalajs.dom.raw.Event
import org.scalajs.jquery.jQuery

@JSExportTopLevel("Frontend")
object Frontend {
  scribe.info("Frontend loaded!");

  @JSExport
  def backToTop(): Unit = {
    jQuery("html, body").animate(js.Dictionary("scrollTop" -> 0), "50");
  }
}
