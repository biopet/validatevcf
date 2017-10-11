package nl.biopet.tools.validatevcf

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class ValidateVcfTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ValidateVcf.main(Array())
    }
  }
}
