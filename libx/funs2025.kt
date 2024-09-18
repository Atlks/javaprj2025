package libx

// "C:\Program Files\JetBrains\IntelliJ IDEA 2024.2.1\plugins\maven\lib\maven3\bin\mvn.cmd" clean install -U


fun encodeJson(map: Map<String, Any>): String {
    // 简单示例，返回一个 JSON 字符串
    return map.entries.joinToString(", ", "{", "}") { "\"${it.key}\": ${it.value}" }
}