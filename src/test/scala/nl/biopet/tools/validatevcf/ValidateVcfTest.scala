package nl.biopet.tools.validatevcf

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object ValidateVcfTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ToolTemplate.main(Array())
    }
  }
}
