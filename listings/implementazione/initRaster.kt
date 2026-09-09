init {
    require(latitudes.isStrictlyAscending()) {
        "latitudes must be ..."
    }
    require(longitudes.isStrictlyAscending()) {
        "longitudes must be ..."
    }
    val expectedSize = latitudes.size * longitudes.size
    require(gridValues.size == expectedSize) {
        "Dimension mismatch: ..."
    }
}