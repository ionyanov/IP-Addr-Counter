import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MainKtTest {
    @Test
    fun testCountIPv1() {
        assertEquals(4, countIPv1("test/test_data1"))
        assertEquals(7, countIPv1("test/test_data2"))
    }

    @Test
    fun testCountIPv2() {
        assertEquals(4, countIPv2("test/test_data1", false, 0))
        assertEquals(7, countIPv2("test/test_data2", false, 0))
    }

    @Test
    fun testIp2Num_ValidIps() {
        assertEquals(3232235777, ip2num("192.168.1.1"))
        assertEquals(2130706433, ip2num("127.0.0.1"))
        assertEquals(0, ip2num("0.0.0.0"))
        assertEquals(4294967295, ip2num("255.255.255.255"))
        assertEquals(16843009, ip2num("1.1.1.1"))
    }

    @Test
    fun testIp2Num_EdgeCases() {
        assertEquals(1, ip2num("0.0.0.1"))
        assertEquals(256, ip2num("0.0.1.0"))
        assertEquals(65536, ip2num("0.1.0.0"))
        assertEquals(16777216, ip2num("1.0.0.0"))
    }
}