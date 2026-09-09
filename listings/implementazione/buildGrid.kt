internal fun buildGrid(latitudes: DoubleArray, longitudes: DoubleArray, measurements: DoubleArray): RasterGrid {
    val density = measurements.count { !it.isNaN() }.toDouble() / measurements.size
    return if (density < SPARSE_DENSITY_THRESHOLD) {
        MapRasterGrid(latitudes, longitudes, measurements)
    } else {
        ArrayRasterGrid(latitudes, longitudes, measurements)
    }
}