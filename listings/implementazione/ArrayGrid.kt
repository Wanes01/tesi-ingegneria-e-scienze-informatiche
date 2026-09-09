// implementazione di valueAt in ArrayRasterGrid
override fun valueAt(latIndex: Int, lonIndex: Int): Double = gridValues[latIndex * longitudes.size + lonIndex]

// implementazione di valueAt in MapRasterGrid
override fun valueAt(latIndex: Int, lonIndex: Int): Double =
    availableValues.getOrDefault(latIndex to lonIndex, Double.NaN)
