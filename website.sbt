val commonSettings = Seq(
  organization := "se.kth.plds",
  version := "1.0.0-SNAPSHOT",
  scalaVersion := "2.13.1",
  scalacOptions ++= Seq("-deprecation", "-feature"),
  resolvers += Resolver.mavenLocal
);

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "PLDS Website"
  )
  .aggregate(frontend, generator);

lazy val frontend = (project in file("frontend"))
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .settings(
    commonSettings,
    name := "PLDS Website Frontend",
    libraryDependencies ++= Seq(
      "com.outr" %%% "scribe" % "2.7.10",
      "com.lihaoyi" %%% "scalatags" % "0.8.2",
      "org.scala-js" %%% "scalajs-dom" % "0.9.7",
      "be.doeraene" %%% "scalajs-jquery" % "0.9.6",
      "org.scalatest" %%% "scalatest" % "3.1.0" % "test"
    )
  );

lazy val generator = (project in file("generator"))
  .enablePlugins(SbtWeb)
  .settings(
    commonSettings,
    name := "Benchmark Suite Visualisation Generator",
    libraryDependencies ++= Seq(
      "org.rogach" %% "scallop" % "3.3.2",
      "com.lihaoyi" %% "scalatags" % "0.8.2",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.+",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.scalatest" %% "scalatest" % "3.1.0" % "test"
    ),
    scalaJSProjects := Seq(frontend),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    WebKeys.packagePrefix in Assets := "public/",
    managedClasspath in Runtime += (packageBin in Assets).value,
    fork := true,
    assemblyMergeStrategy in assembly := {
      case "logback.xml"        => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  );
