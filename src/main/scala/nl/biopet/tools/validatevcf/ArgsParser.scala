package nl.biopet.tools.validatevcf

import java.io.File

import nl.biopet.utils.tool.AbstractOptParser

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {
  opt[File]('i', "inputVcf") required () maxOccurs 1 valueName "<file>" action {
    (x, c) =>
      c.copy(inputVcf = x)
  } text "Vcf file to check"
  opt[File]('R', "reference") required () maxOccurs 1 valueName "<file>" action {
    (x, c) =>
      c.copy(reference = x)
  } text "Reference fasta to check vcf file against"
  opt[Unit]("disableFail") maxOccurs 1 valueName "<file>" action { (_, c) =>
    c.copy(failOnError = false)
  } text "Do not fail on error. The tool will still exit when encountering an error, but will do so with exit code 0"
}
