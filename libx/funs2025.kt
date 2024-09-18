package libx


fun encodeJson(map: Map<String, Any>): String {
    // 简单示例，返回一个 JSON 字符串
    return map.entries.joinToString(", ", "{", "}") { "\"${it.key}\": ${it.value}" }
}