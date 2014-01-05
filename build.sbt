name := "smarter-playlists"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.apache.commons" % "commons-io" % "1.3.2"
)     

play.Project.playJavaSettings
