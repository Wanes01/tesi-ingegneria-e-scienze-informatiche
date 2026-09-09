data class CopernicusRequest(
    val endpoint: String,
    val dataset: String,
    val inputs: Map<String, Any>,
) : CacheKey {
    // ...
    override fun toDirectoryName(): String {
        val canonical = CanonicalJson.encode(
            mapOf(
                "endpoint" to endpoint,
                "dataset" to dataset,
                "inputs" to inputs,
            ),
        )
        return "${dataset.toFileSystemSafe()}_${sha256Hex(canonical).take(HASH_PREFIX_LENGTH)}"
    }
    // ...
}

internal fun String.toFileSystemSafe(): String =
    this.replace(Regex("[^A-Za-z0-9._-]"), "_")
