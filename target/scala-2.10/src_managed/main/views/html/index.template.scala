
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[String,String,String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(section: String)(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.51*/("""

"""),_display_(Seq[Any](/*3.2*/main(title)/*3.13*/(section)/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""

    """),format.raw/*5.51*/("""

""")))})),format.raw/*7.2*/("""
"""))}
    }
    
    def render(title:String,section:String,message:String): play.api.templates.HtmlFormat.Appendable = apply(title)(section)(message)
    
    def f:((String) => (String) => (String) => play.api.templates.HtmlFormat.Appendable) = (title) => (section) => (message) => apply(title)(section)(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Wed Nov 27 23:57:23 CST 2013
                    SOURCE: /Users/blevy/workspace/smarterplaylists/app/views/index.scala.html
                    HASH: e789a46637a8f6d5954598127f7d90dbae51bac3
                    MATRIX: 788->1|931->50|968->53|987->64|1004->73|1043->75|1076->127|1109->130
                    LINES: 26->1|29->1|31->3|31->3|31->3|31->3|33->5|35->7
                    -- GENERATED --
                */
            