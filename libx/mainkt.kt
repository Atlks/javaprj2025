package libx

// Main.kt

fun main() {
    val map = mapOf("key" to 55993)
    val json = encodeJson(map)
    println(json) // 输出: {"key": 553}
}


//