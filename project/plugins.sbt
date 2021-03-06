resolvers ++= Seq(
  "Sonatype snapshots"                                 at "http://oss.sonatype.org/content/repositories/snapshots/",
  "jgit-repo"                                          at "http://download.eclipse.org/jgit/maven",
  "Twitter Repo"                                       at "http://maven.twttr.com/",
  "sonatype-releases"                                  at "https://oss.sonatype.org/content/repositories/releases/",
  Resolver.bintrayRepo("websudos", "oss-releases")
)

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.7.4")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.6.4")

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.5.0")

addSbtPlugin("com.twitter" %% "scrooge-sbt-plugin" % "3.18.1")

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.0.4")

addSbtPlugin("org.scoverage" %% "sbt-coveralls" % "1.0.0.BETA1")

addSbtPlugin("com.websudos" % "sbt-package-dist" % "1.2.0")