override fun getOrProduce(request: R): Path {
    val cacheKey = request.toDirectoryName()
    val finalDir = root.resolve(cacheKey)
    if (Files.isDirectory(finalDir)) {
        return finalDir
    }
    val monitor = entryLocks.computeIfAbsent(finalDir) { Any() }
    return synchronized(monitor) {
        // a peer may have produced the entry while this caller was waiting for the monitor
        if (Files.isDirectory(finalDir)) {
            finalDir
        } else {
            produce(request, cacheKey, finalDir)
        }
    }
}

companion object {
    // ...
    private val entryLocks = ConcurrentHashMap<Path, Any>()
}