package se.kth.plds.generator

import scalatags.Text.all._
import scalatags.stylesheet._

object StandardStyle extends StyleSheet {
  override def customSheetName = Some("plds");

  initStyleSheet()

  val titleBackground = cls(
    backgroundColor := "rgb(0,0,0,0.7)",
    display.`inline-block`,
    padding := 1.em,
    borderRadius := "0px 15px 0px 0px"
  );

  val titleText = cls(
    fontSize := 2.em,
    textAlign.left,
    color := "white"
  );

  val subtitleText = cls(
    fontSize := 1.2.em,
    textAlign.left,
    color := "white"
  );

  val timeLocationText = cls(
    fontSize := 1.0.em,
    textAlign.left,
    color := "white"
  );

  val headline = cls(
    textDecoration := "underline"
  );

  val hidden = cls(
    display := "none"
  );

  val mainContent = cls(
    paddingTop := 1.em
  );
}
