
import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.math.pow

/**
 *
 */
fun main(args: Array<String>) = try {
    val ar = MainArgParser(args)
    if(ar.methodName == MethodVersion.v1)
        println("V1. In file ${ar.fileName} exist ${countIPv1(fileName = ar.fileName)} uniq IP")
    else
        println("V2. In file ${ar.fileName} exist ${countIPv2(fileName = ar.fileName, rowLimit = ar.rowLimits, showProcess = ar.showProgress)} uniq IP")
}
catch (e: Exception) {
    println(e.message)
}

/**
 * Count uniq IP addresses in file.
 * NOT WORK FOR LARGE FILE!!!!
 * @fileName fileName for processing
 */
fun countIPv1(fileName: String): Number?  {
    var result: Number? = null
    val inputStream: InputStream = File(fileName).inputStream()
    try {
        val lineList = mutableListOf<String>()
        inputStream.bufferedReader().forEachLine {lineList.add(it)}
        result = lineList.distinct().count()
    }
    catch (e: Exception) {
        println(e.message)
    }
    return result
}

/**
 * Count uniq IP addresses in file.
 * @fileName fileName for processing
 * @showProcess if TRUE will print processed row count. default FALSE
 * @rowLimit count of row for show processing message. default 1 000 000
 */
fun countIPv2(fileName: String, showProcess: Boolean, rowLimit: Long): Number?  {
    var result: Number? = null
    try {
        var rowCnt: Long = 0
        val inputStream: InputStream = File(fileName).inputStream()
        val posBSData = BitSet()
        val negBSData = BitSet()
        inputStream.bufferedReader().forEachLine {
            val itm = ip2num(it).toInt()
            if (itm>0) posBSData.set(itm)
            else negBSData.set(itm*-1)
            if(showProcess){
                rowCnt += 1
                if(rowCnt%rowLimit == 0.toLong()) println(rowCnt)
            }
        }
        result = posBSData.cardinality() + negBSData.cardinality()
    }
    catch (e: Exception) {
        println(e.message)
    }
    return result
}

/**
 * Convert IP in string format to Long.
 * Use formula a.b.c.d => a * 256^3 + b * 256^2 + c * 256 + d
 * @ip IP address
 */
fun ip2num(ip: String): Long {
    /*val ipAddressInArray: List<String> = ip.split(".")
    ipAddressInArray.forEachIndexed { index, s ->
        val ip: Int = s.toInt()
        result += (ip * 256.0.pow(3-index)).toLong()
    }*/
    var result: Long = 0
    var tmpStr = ""
    var itemIndex = 0
    ("$ip.").forEach{ s ->
        if(s=='.') {
            result += (tmpStr.toInt() * 256.0.pow(3-itemIndex)).toLong()
            itemIndex += 1
            tmpStr = ""
        }
        else
        {
            tmpStr += s
        }
    }
    return result
}

