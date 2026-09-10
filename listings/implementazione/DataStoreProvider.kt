private fun awaitSuccess(monitorUrl: String): String {
    val deadline = TimeSource.Monotonic.markNow() + timeout
    var interval = pollInterval
    while (true) {
        val body = get(monitorUrl).body()
        when (val status = parseStatus(body)) {
            "successful" -> return parseResultsUrl(body)
                ?: error("Job 'successful' but no rel='results' link at $monitorUrl: inconsistent response")
            in TERMINAL_STATUSES -> failOnStatus(monitorUrl, status, body)
            in RUNNING_STATUSES -> { /* ... */ }
            // unknown status: warns the user, but keeps polling
            else -> logger.warn("Unrecognized job status '$status' at $monitorUrl, continuing to poll")
        }
        check(deadline.hasNotPassedNow()) {
            "Timeout ($timeout) while waiting for job completion at $monitorUrl"
        }
        Thread.sleep(interval.inWholeMilliseconds)
        interval = (interval * 2).coerceAtMost(maxPollInterval)
    }
}