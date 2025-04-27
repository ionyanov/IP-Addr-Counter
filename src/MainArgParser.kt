enum class MethodVersion { v1, v2 }

/**
 * Input argument parser
 */
class MainArgParser(args: Array<String>) {
    private var last = ""
    private var map: Map<String, List<String>> = args.fold(mutableMapOf()) {
            acc: MutableMap<String, MutableList<String>>, s: String ->
        acc.apply {
            if (s.startsWith('-'))
            {
                this[s] = mutableListOf()
                last = s
            }
            else this[last]?.add(s)
        }
    }

    /**
     * File Name for processing
     */
    val fileName: String
        get() { return this.map["-f"]?.first() ?: "ip_addresses"}

    /**
     * Flag indicate Need show processing or Not
     */
    val showProgress: Boolean
        get() { return this.map.containsKey("-p") }

    /**
     * Row count for print processing messages
     */
    val rowLimits: Long
        get() { return this.map["-r"]?.first()?.toLongOrNull() ?: 1000000 }

    /**
     * Method type for use
     */
    val methodName: MethodVersion
        get() {
            return if (this.map.containsKey("-v"))
                if(this.map["-v"]?.isNotEmpty() ?: false) {
                    if((this.map["-v"]?.first() ?: "2") == "1") MethodVersion.v1 else MethodVersion.v2
                } else MethodVersion.v2
            else MethodVersion.v2
        }


}