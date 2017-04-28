addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.4")
addSbtPlugin("org.xerial.sbt" % "sbt-pack" % "0.8.2")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC1")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.1.0")

// Dependency tree check
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

scalacOptions ++= Seq("-deprecation", "-feature")

