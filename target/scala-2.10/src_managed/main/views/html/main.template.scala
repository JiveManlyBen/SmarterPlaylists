
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(section: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.49*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
	<link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,300italic,700" rel="stylesheet">
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*9.54*/routes/*9.60*/.Assets.at("stylesheets/style.css"))),format.raw/*9.95*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*10.59*/routes/*10.65*/.Assets.at("images/favicon.png"))),format.raw/*10.97*/("""">
        <script src=""""),_display_(Seq[Any](/*11.23*/routes/*11.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*11.74*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*12.16*/routes/*12.22*/.Assets.at("javascripts/config.js"))),format.raw/*12.57*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*13.16*/routes/*13.22*/.Assets.at("javascripts/skel.min.js"))),format.raw/*13.59*/("""" type="text/javascript"></script>
	<script src=""""),_display_(Seq[Any](/*14.16*/routes/*14.22*/.Assets.at("javascripts/skel-panels.min.js"))),format.raw/*14.66*/("""" type="text/javascript"></script>
    </head>
    <body>
		<!-- Header -->

			<div id="header-wrapper">
				<header class="container" id="site-header">
					<div class="row">
						<div class="12u">
							<div id="logo">
								<h1>Smarter Playlists</h1>
							</div>
							<nav id="nav">
								<ul>
									<li """),_display_(Seq[Any](/*28.15*/if(section.equals("home"))/*28.41*/{_display_(Seq[Any](format.raw/*28.42*/(""" class="current_page_item"""")))})),format.raw/*28.69*/("""><a href="/">Home</a></li>
									<li """),_display_(Seq[Any](/*29.15*/if(section.equals("about"))/*29.42*/{_display_(Seq[Any](format.raw/*29.43*/(""" class="current_page_item"""")))})),format.raw/*29.70*/("""><a href="/about">About</a></li>
								</ul>
							</nav>
						</div>
					</div>
				</header>
			</div>

		<!-- Main -->

			<div id="main-wrapper">
				<div class="container">

					<!-- Divider -->

						<div class="row">
							<div class="12u">
								<div class="divider divider-top"></div>
							</div>
						</div>

					<!-- Highlight Box -->

						<div class="row">
							<div class="12u">
								<div class="highlight-box">
									<h2>Amet lorem varius tempus consequat lorem?</h2>
									<span>(tempus aliquam lorem blandit etiam suspendisse dapibus)</span>
								</div>
							</div>
						</div>

					<!-- CTA Box -->

						<div class="row">
							<div class="12u">
								<div class="cta-box">
									<span>Amet lorem varius tempus consequat lorem?</span>
									<a href="#" class="button">Ipsum Consequat</a>
								</div>
							</div>
						</div>

					<!-- Divider -->

						<div class="row">
							<div class="12u">
								<div class="divider divider-bottom"></div>
							</div>
						</div>
				</div>
			</div>

        """),_display_(Seq[Any](/*82.10*/content)),format.raw/*82.17*/("""
		<!-- Footer -->

			<div id="footer-wrapper">
				<footer class="container" id="site-footer">
					<div class="row">
						<div class="12u">
							<div class="divider"></div>
						</div>
					</div>
					<div class="row">
						<div class="12u">
							<div id="copyright">
								&copy; Ben Levy. All rights reserved. | Design: <a href="http://html5up.net" target="_blank">HTML5 UP</a>
							</div>
						</div>
					</div>
				</footer>
			</div>
    </body>
</html>
"""))}
    }
    
    def render(title:String,section:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(section)(content)
    
    def f:((String) => (String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (section) => (content) => apply(title)(section)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Nov 27 23:47:21 CST 2013
                    SOURCE: /Users/blevy/workspace/smarterplaylists/app/views/main.scala.html
                    HASH: 680a716fbf59502f5ad1dabc58695e02f8ef67c7
                    MATRIX: 785->1|926->48|1014->101|1040->106|1246->277|1260->283|1316->318|1413->379|1428->385|1482->417|1543->442|1558->448|1625->493|1711->543|1726->549|1783->584|1869->634|1884->640|1943->677|2029->727|2044->733|2110->777|2468->1099|2503->1125|2542->1126|2601->1153|2678->1194|2714->1221|2753->1222|2812->1249|3922->2323|3951->2330
                    LINES: 26->1|29->1|35->7|35->7|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|56->28|56->28|56->28|56->28|57->29|57->29|57->29|57->29|110->82|110->82
                    -- GENERATED --
                */
            