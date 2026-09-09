internal object CanonicalJson {
    // ...
    fun encode(value: Any): String = gson.toJson(canonicalize(value))

    private fun canonicalize(value: Any?): Any? = when (value) {
        // keeps the keys sorted
        is Map<*, *> -> TreeMap<String, Any?>().apply {
            value.forEach { (key, mapValue) ->
                put(key.toString(), canonicalize(mapValue))
            }
        }
        is List<*> -> value.map(::canonicalize)
        else -> value
    }
}