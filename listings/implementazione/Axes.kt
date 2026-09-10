internal fun nearestIndex(axis: DoubleArray, coordinate: Double): Int {
    require(axis.isNotEmpty()) { "Axis cannot be empty." }
    require(!coordinate.isNaN()) { "The query coordinate cannot be NaN." }
    val binarySearchResult = Arrays.binarySearch(axis, coordinate)
    // binarySearch returns -(insertionPoint) - 1 when no match is found.
    val upperIndex = -binarySearchResult - 1
    return when {
        binarySearchResult >= 0 -> binarySearchResult
        upperIndex <= 0 -> 0
        upperIndex >= axis.size -> axis.lastIndex
        else -> {
            val lowerIndex = upperIndex - 1
            val distanceToLower = coordinate - axis[lowerIndex]
            val distanceToUpper = axis[upperIndex] - coordinate
            if (distanceToLower <= distanceToUpper) lowerIndex else upperIndex
        }
    }
}