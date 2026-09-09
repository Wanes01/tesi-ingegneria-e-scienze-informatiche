internal data class ProblemDetail( /* ... */ ) {
    fun describe(): String {
        val core = detail ?: traceback ?: title ?: return ""
        return buildString {
            append(core)
            append(" [type=$type")
            traceId?.let { append(", trace=$it") }
            append("]")
        }
    }
}