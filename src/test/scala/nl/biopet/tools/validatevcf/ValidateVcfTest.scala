package nl.biopet.tools.validatevcf

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

class ValidateVcfTest extends ToolTest[Args] {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ValidateVcf.main(Array())
    }
  }

  @Test
  def testMain(): Unit = {
    noException shouldBe thrownBy {
      ValidateVcf.main(
        Array("-i", resourcePath("/chrQ2.vcf"), "-R", resourcePath("/fake_chrQ.fa")))
    }
    an[IllegalArgumentException] shouldBe thrownBy {
      ValidateVcf.main(
        Array("-i", resourcePath("/chrQ_wrong_contig.vcf"), "-R", resourcePath("/fake_chrQ.fa")))
    }
    noException shouldBe thrownBy {
      ValidateVcf.main(
        Array("-i",
          resourcePath("/chrQ_wrong_contig.vcf"),
          "-R",
          resourcePath("/fake_chrQ.fa"),
          "--disableFail"))
    }
  }
}