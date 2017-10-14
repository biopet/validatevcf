package nl.biopet.tools.validatevcf

import java.io.File

case class Args(inputVcf: File = null,
                reference: File = null,
                failOnError: Boolean = true)
