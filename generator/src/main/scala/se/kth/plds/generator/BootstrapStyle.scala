package se.kth.plds.generator

import scalatags.Text.all._

object BootstrapStyle {

  val active = cls := "active";
  val alert = cls := "alert";
  val alertInfo = cls := "alert-info";
  val bgLight = cls := "bg-light";
  val bgDark = cls := "bg-dark";
  val container = cls := "container";
  val containerFluid = cls := "container-fluid";
  val display1 = cls := "display-1";
  val display2 = cls := "display-2";
  val display3 = cls := "display-3";
  val floatRight = cls := "float-right";
  val label = cls := "label";
  val lead = cls := "lead";
  val listGroup = cls := "list-group";
  val listGroupItem = cls := "list-group-item";
  val listGroupItemAction = cls := "list-group-item-action";
  val listGroupFlush = cls := "list-group-flush";
  val breadcrumb = cls := "breadcrumb";
  val breadcrumbItem = cls := "breadcrumb-item";
  val navbarExpand = cls := "navbar-expand-sm";
  val navbar = cls := "navbar";
  val navbarDark = cls := "navbar-dark";
  val navbarNav = cls := "navbar-nav";
  val nav = cls := "nav";
  val navItem = cls := "nav-item";
  val navLink = cls := "nav-link";
  val flexColumn = cls := "flex-column";
  val btn = cls := "btn";
  val btnSecondary = cls := "btn-secondary";
  val btnSmall = cls := "btn-sm";
  val shadow = cls := "shadow";
  val shadowSmall = cls := "shadow-sm";
  val imgFluid = cls := "img-fluid";
  val textJustify = cls := "text-justify";
  val textCentre = cls := "text-center";
  val textLight = cls := "text-light";
  val textMuted = cls := "text-muted";

  // Grid
  val row = cls := "row";
  val col = cls := "col";
  def colSmall(num: Int) =
    cls := (if (num == 0) {
              "col-sm"
            } else {
              s"col-sm-$num"
            });

  // Cards
  val card = cls := "card";
  val cardHeader = cls := "card-header";
  val cardBody = cls := "card-body";
}
