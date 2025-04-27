import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MainArgParserTest {

    @Test
    fun testDefaultValues() {
         val args = arrayOf<String>()
         val mainArgs = MainArgParser(args)

         assertEquals("ip_addresses", mainArgs.fileName)
         assertFalse(mainArgs.showProgress)
         assertEquals(1_000_000, mainArgs.rowLimits)
         assertEquals(MethodVersion.v2, mainArgs.methodName)
    }

    @Test
    fun testFileNameProvided() {
        val args = arrayOf("-f", "custom_file")
        val mainArgs = MainArgParser(args)

        assertEquals("custom_file", mainArgs.fileName)
    }

    @Test
    fun testShowProgressProvided() {
        val args = arrayOf("-p")
        val mainArgs = MainArgParser(args)

        assertTrue(mainArgs.showProgress)
    }

    @Test
    fun testRowLimitsProvided() {
         val args = arrayOf("-r", "5000")
         val mainArgs = MainArgParser(args)

         assertEquals(5000, mainArgs.rowLimits)
    }

    @Test
    fun testRowLimitsInvalidDefaultsToOneMillion() {
         val args = arrayOf("-r", "not_a_number")
         val mainArgs = MainArgParser(args)

         assertEquals(1_000_000, mainArgs.rowLimits)
    }

    @Test
    fun testMethodNameVersion1() {
         val args = arrayOf("-v", "1")
         val mainArgs = MainArgParser(args)

         assertEquals(MethodVersion.v1, mainArgs.methodName)
    }

    @Test
    fun testMethodNameVersion2Explicit() {
         val args = arrayOf("-v", "2")
         val mainArgs = MainArgParser(args)

         assertEquals(MethodVersion.v2, mainArgs.methodName)
    }

    @Test
    fun testMethodNameVersion2Default() {
         val args = arrayOf("-v")
         val mainArgs = MainArgParser(args)

         assertEquals(MethodVersion.v2, mainArgs.methodName)
    }

    @Test
    fun testMixedArguments() {
         val args = arrayOf("-f", "data_file", "-p", "-r", "1234", "-v", "1")
         val mainArgs = MainArgParser(args)

         assertEquals("data_file", mainArgs.fileName)
         assertTrue(mainArgs.showProgress)
         assertEquals(1234, mainArgs.rowLimits)
         assertEquals(MethodVersion.v1, mainArgs.methodName)
    }
}
