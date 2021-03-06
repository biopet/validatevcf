/*
 * Copyright (c) 2014 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools.validatevcf

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

class ValidateVcfTest extends ToolTest[Args] {
  def toolCommand: ValidateVcf.type = ValidateVcf
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
        Array("-i",
              resourcePath("/chrQ2.vcf"),
              "-R",
              resourcePath("/fake_chrQ.fa")))
    }

    an[IllegalArgumentException] shouldBe thrownBy {
      ValidateVcf.main(
        Array("-i",
              resourcePath("/chrQ_wrong_contig.vcf"),
              "-R",
              resourcePath("/fake_chrQ.fa")))
    }

    intercept[IllegalArgumentException] {
      ValidateVcf.main(
        Array("-i",
              resourcePath("/chrQ_wrong_ref.vcf"),
              "-R",
              resourcePath("/fake_chrQ.fa")))
    }.getMessage.startsWith(
      "requirement failed: Reference sequence is not the same") shouldBe true

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
