import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

val SCALA_VERSION = "2.12.1"
scalaVersion in ThisBuild := SCALA_VERSION

val sonatypeRepos = Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

val buildSettings = Seq[Setting[_]](
  crossScalaVersions := Seq("2.10.6", "2.11.8", SCALA_VERSION),
  organization := "org.wvlet",
  crossPaths := true,
  publishMavenStyle := true,
  // For performance testing, ensure each test run one-by-one
  concurrentRestrictions in Global := Seq(Tags.limit(Tags.Test, 1)),
  incOptions := incOptions.value.withNameHashing(true),
  logBuffered in Test := false,
  updateOptions := updateOptions.value.withCachedResolution(true),
  sonatypeProfileName := "org.wvlet",
  pomExtra := {
    <url>https://github.com/wvlet/sql</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
        </license>
      </licenses>
      <scm>
        <connection>scm:git:github.com/wvlet/sql.git</connection>
        <developerConnection>scm:git:git@github.com:wvlet/sql.git</developerConnection>
        <url>github.com/wvlet/sql.git</url>
      </scm>
      <developers>
        <developer>
          <id>leo</id>
          <name>Taro L. Saito</name>
          <url>http://xerial.org/leo</url>
        </developer>
      </developers>
  },
  // Use sonatype resolvers
  resolvers ++= sonatypeRepos,
  // Release settings
  releaseTagName := {(version in ThisBuild).value},
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    ReleaseStep(action = Command.process("publishSigned", _), enableCrossBuild = true),
    setNextVersion,
    commitNextVersion,
    ReleaseStep(action = Command.process("sonatypeReleaseAll", _), enableCrossBuild = true),
    pushChanges
  ),
  releaseCrossBuild := true
)

lazy val wvletSql = Project(id="wvlet-sql", base = file("."))
  .settings(buildSettings)
  .settings(
    description := "Bridging gaps between SQL and Scala collections",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
