package se.kth.plds.generator

import scalatags.Text.all._
import scalatags.Text.tags2.nav
import scalatags.generic.Attr

object Frame {
  val integrity: Attr = Attr("integrity");
  val crossorigin: Attr = Attr("crossorigin");

  val headers = head(
    scalatags.Text.tags2.title("PLDS 2020"),
    link(rel := "stylesheet", `type` := "text/css", href := "bootstrap.min.css"),
    link(rel := "stylesheet", `type` := "text/css", href := "main.css"),
    link(rel := "stylesheet", `type` := "text/css", href := "standard.css"),
    script(src := "https://code.jquery.com/jquery-2.2.4.min.js",
           integrity := "sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=",
           crossorigin := "anonymous"),
    script(src := "https://kit.fontawesome.com/fed54ae85d.js", crossorigin := "anonymous"),
    script(
      src := "https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js",
      integrity := "sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6",
      crossorigin := "anonymous"
    ),
    script(src := "plds-website-frontend.js")
  );

  val ariaCurrent = attr("aria-current");

  val pages: Seq[Page] = Seq(IndexPage, ProgrammePage, VenuePage);

  lazy val header = div(
    BootstrapStyle.containerFluid,
    BootstrapStyle.shadow,
    cls := "header-image",
    div(
      StandardStyle.titleBackground,
      h1(StandardStyle.titleText, BootstrapStyle.display1, "PLDS'20 Workshop"),
      h2(StandardStyle.subtitleText, BootstrapStyle.display2, "Programming Languages and Distributed Systems"),
      h3(StandardStyle.timeLocationText,
         BootstrapStyle.display3,
         "March 5th & 6th 2020 at RISE Computer Science, Electrum Kista, Stockholm, Sweden")
    )
  );

  def embed(content: Tag, page: Page): String =
    "<!DOCTYPE html>" + html(
      headers,
      body(
        div(
          BootstrapStyle.container,
          header,
          nav(
            BootstrapStyle.shadow,
            BootstrapStyle.navbarExpand,
            BootstrapStyle.navbar,
            BootstrapStyle.navbarDark,
            BootstrapStyle.bgDark,
            ul(
              BootstrapStyle.navbarNav,
              for (p <- pages) yield {
                p match {
                  case `page` =>
                    li(BootstrapStyle.active,
                       BootstrapStyle.navItem,
                       a(BootstrapStyle.navLink, href := p.file, p.title))
                  case _ => li(BootstrapStyle.navItem, a(BootstrapStyle.navLink, href := p.file, p.title))
                }
              }
            )
            // title match {
            //   case IndexPage.title =>
            //     nav(aria.label := "breadcrumb",
            //         ol(BootstrapStyle.breadcrumb,
            //            li(BootstrapStyle.active, BootstrapStyle.breadcrumbItem, ariaCurrent := "page", title)))
            //   case _ =>
            //     nav(
            //       aria.label := "breadcrumb",
            //       ol(
            //         BootstrapStyle.breadcrumb,
            //         li(BootstrapStyle.breadcrumbItem, a(href := "index.html", IndexPage.title)),
            //         li(BootstrapStyle.active, BootstrapStyle.breadcrumbItem, ariaCurrent := "page", title)
            //       )
            //     )
            // }
          ),
          div(BootstrapStyle.containerFluid, StandardStyle.mainContent, content)
        )
      )
    ).render;

  val backToTopButton = div(
    BootstrapStyle.floatRight,
    button(`type` := "button",
           BootstrapStyle.btn,
           BootstrapStyle.btnSecondary,
           BootstrapStyle.btnSmall,
           "back to top",
           onclick := "Frontend.backToTop();")
  );
}
