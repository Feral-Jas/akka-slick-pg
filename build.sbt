lazy val akkaHttpVersion = "10.2.4"
lazy val akkaVersion    = "2.6.15"
lazy val slickVersion = "3.2.3"
lazy val logbackVersion = "1.2.3"
lazy val pgVersion = "42.2.18"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "me.liuchenyu",
      scalaVersion    := "2.12.11"
    )),
    name := "akka-http-slick-pg",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json"     % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
      "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
      "ch.qos.logback"    % "logback-classic"           % logbackVersion,
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
      "org.postgresql" % "postgresql" % pgVersion,
      "com.typesafe.akka" %% "akka-http-testkit"        % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion     % Test,
      "org.scalatest"     %% "scalatest"                % "3.1.4"         % Test
    )
  )
