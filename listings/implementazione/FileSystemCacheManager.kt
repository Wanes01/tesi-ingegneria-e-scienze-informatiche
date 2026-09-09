// rimosso il logginggggggggggggggggg
private fun produce(request: R, cacheKey: String, finalDir: Path): Path {
    val temp = Files.createTempDirectory(tmpRoot, cacheKey)
    var promoted = false
    try {
        provider.fetch(request, temp)
        check(hasData(temp)) { "Provider produced no files for '$cacheKey'" }
        promoted = promote(temp, finalDir)
        return finalDir
    } finally {
        // deletes the temp directory if any accident occurred
        if (!promoted) { /* ... */ }
    }
}

private fun promote(temp: Path, finalDir: Path): Boolean = try {
    Files.move(temp, finalDir, StandardCopyOption.ATOMIC_MOVE)
    true
} catch (raceLost: FileSystemException) {
    if (!Files.isDirectory(finalDir)) throw raceLost
    // another process won
    false
}