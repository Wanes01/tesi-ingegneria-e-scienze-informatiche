fun rasterGridContract(gridOf: (DoubleArray, DoubleArray, DoubleArray) -> RasterGrid) = stringSpec {
    // ...
    "isInBounds should return whether the position falls in the spatial extent" {
        fun doubleSequence(start: Double, stop: Double, step: Double) =
            generateSequence(start) { it + step }.takeWhile { it <= stop }
        val step = 0.5
        val latRange = doubleSequence(lats.first(), lats.last(), step)
        val lonRange = doubleSequence(lons.first(), lons.last(), step)
        for (lat in latRange) {
            for (lon in lonRange) {
                grid.isInBounds(mockGeoPosition(lat, lon)).shouldBeTrue()
            }
        }
        grid.isInBounds(mockGeoPosition(lats.last() + step, lons.last() + step)).shouldBeFalse()
    }
    // ...
    "axes that are not strictly increasing should raise an exception" {
        shouldThrow<IllegalArgumentException> {
            gridOf(lats.reversedArray(), lons, values)
        }
        shouldThrow<IllegalArgumentException> {
            gridOf(lats, lons.reversedArray(), values)
        }
    }
    // ...
}

// tests the array implementation (suitable for dense grids)
class TestArrayRasterGrid : StringSpec({
    include(rasterGridContract(::ArrayRasterGrid))
})

// tests the map implementation (suitable for sparse grids)
class TestMapRasterGrid : StringSpec({
    include(rasterGridContract(::MapRasterGrid))
})
