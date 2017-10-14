package nl.biopet.tools.validatevcf

import htsjdk.variant.vcf.VCFFileReader
import nl.biopet.utils.ngs.intervals.BedRecordList
import nl.biopet.utils.tool.ToolCommand

import scala.collection.JavaConversions._

object ValidateVcf extends ToolCommand {
  def main(args: Array[String]): Unit = {
    val parser = new ArgsParser(toolName)
    val cmdArgs =
      parser.parse(args, Args()).getOrElse(throw new IllegalArgumentException)

    logger.info("Start")

    val regions = BedRecordList.fromReference(cmdArgs.reference)

    val vcfReader = new VCFFileReader(cmdArgs.inputVcf, false)

    try {
      for (record <- vcfReader.iterator()) {
        val contig = record.getContig
        require(regions.chrRecords.contains(contig),
          s"The following contig in the vcf file does not exist in the reference: $contig")
        val start = record.getStart
        val end = record.getEnd
        val contigStart = regions.chrRecords(contig).head.start
        val contigEnd = regions.chrRecords(contig).head.end
        require(start >= contigStart && start <= contigEnd,
          s"The following position does not exist on reference: $contig:$start")
        if (end != start)
          require(end >= contigStart && end <= contigEnd,
            s"The following position does not exist on reference: $contig:$end")
        require(
          start <= end,
          "End location of variant is larger than start position. This should not be possible")
      }
    } catch {
      case e: IllegalArgumentException =>
        if (cmdArgs.failOnError) throw e
        else logger.error(e.getMessage)
    }

    vcfReader.close()

    logger.info("Done")
  }
}
