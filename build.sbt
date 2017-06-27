name := "com.youleligou.360wallpaper"

version := "1.0"

scalaVersion := "2.12.2"

updateOptions := updateOptions.value.withCachedResolution(true)

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    TwirlKeys.templateImports += "com.github.aselab.activerecord.views.dsl._"
  )

routesGenerator := InjectedRoutesGenerator

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.sonatypeRepo("snapshots"),
  Resolver.bintrayRepo("scalaz", "releases"),
  "Atlassian Releases" at "https://maven.atlassian.com/public/",
  Resolver.jcenterRepo
)

libraryDependencies ++= Seq(
  ehcache,
  ws,
  filters,
  specs2,
  // -- database --
  jdbc,
  "com.github.aselab" %% "scala-activerecord" % "0.4.0-SNAPSHOT",
  "com.github.aselab" %% "scala-activerecord-play2" % "0.4.0-SNAPSHOT",
  "mysql" % "mysql-connector-java" % "5.1.40",

  "com.typesafe.play" %% "play-json-joda" % "2.6.0"
)

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
