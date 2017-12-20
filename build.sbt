organization := "com.github.biopet"
organizationName := "Sequencing Analysis Support Core - Leiden University Medical Center"

startYear := Some(2014)

name := "validatevcf"
biopetUrlName := "validatevcf"

biopetIsTool := true

mainClass in assembly := Some("nl.biopet.tools.validatevcf.ValidateVcf")

developers := List(Developer(id="ffinfo", name="Peter van 't Hof", email="pjrvanthof@gmail.com", url=url("https://github.com/ffinfo")))

scalaVersion := "2.11.11"

libraryDependencies += "com.github.biopet" %% "tool-utils" % "0.2"
libraryDependencies += "com.github.biopet" %% "ngs-utils" % "0.1"
libraryDependencies += "com.github.biopet" %% "tool-test-utils" % "0.1" % Test
