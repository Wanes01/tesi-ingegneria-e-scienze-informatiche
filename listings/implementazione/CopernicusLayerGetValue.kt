override fun getValue(position: GeoPosition): T {
    val t = environment.simulationOrNull?.time?.toDouble() ?: 0.0
    val (gridIndexBefore, gridIndexAfter) = bracketIndices(sliceTimes, t)
    return if (gridIndexBefore == gridIndexAfter) {
        // the time t falls exactly on a slice.
        sampleExactSlice(position, gridIndexBefore)
    } else {
        val timeWeight = weight(
            sliceTimes,
            gridIndexBefore,
            gridIndexAfter,
            t,
        )
        sample(position, gridIndexBefore, gridIndexAfter, timeWeight)
    }
}

private fun sample(position: GeoPosition, gridBeforeIndex: Int, gridAfterIndex: Int, timeWeight: Double): T {
    val gridBefore = data.grid(gridBeforeIndex)
    val gridAfter = data.grid(gridAfterIndex)
    require(gridBefore.isInBounds(position)) { outOfBoundsMessage(position, gridBefore) }
    return converter.convert(interpolation.interpolate(position, gridBefore, gridAfter, timeWeight))
}

private fun sampleExactSlice(position: GeoPosition, gridIndex: Int): T =
    sample(position, gridIndex, gridIndex, 0.0)
