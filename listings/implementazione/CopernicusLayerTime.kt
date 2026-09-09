open class CopernicusLayer<T>(
    // ...,
    private val data: GridSnapshots,
    timeScale: Duration,
    timeOrigin: Instant?,
    // ...,
) : GeoLayer<T> {

    init {
        require(data.instants.isNotEmpty()) { "GridSnapshots is empty." }
        require(timeScale.isPositive() && timeScale.isFinite()) { "timeScale must hold a finite positive value." }
    }

    private val sliceTimes: DoubleArray = run {
        val origin: Instant = timeOrigin ?: data.instants.first()
        data.instants
            .map { it.toSimulationTime(origin, timeScale) }
            .toDoubleArray()
    }
    // ...
}

private fun Instant.toSimulationTime(origin: Instant, scale: Duration): Double =
    (this - origin) / scale
