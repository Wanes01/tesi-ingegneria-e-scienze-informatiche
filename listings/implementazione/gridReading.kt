internal fun readPermutedSlice(axes: FileAxes, t: Int, nLat: Int, nLon: Int): CdmArray {
    val origin = IntArray(3).also { array ->
        array[axes.timePosition] = t
        array[axes.latPosition] = 0
        array[axes.lonPosition] = 0
    }
    val shape = IntArray(3).also { array ->
        array[axes.timePosition] = 1
        array[axes.latPosition] = nLat
        array[axes.lonPosition] = nLon
    }
    // rearrange the slice into (time, lat, lon)
    return axes.variable
        .read(origin, shape)
        .permute(intArrayOf(axes.timePosition, axes.latPosition, axes.lonPosition))
        .copy() // permute returns a view
}

internal fun flattenAscending(
    slice: CdmArray,
    nLat: Int,
    nLon: Int,
    latDescending: Boolean,
    lonDescending: Boolean,
): DoubleArray = DoubleArray(nLat * nLon).also { arr ->
    for (idx in arr.indices) {
        val iLat = idx / nLon
        val iLon = idx % nLon
        val srcLat = if (latDescending) nLat - 1 - iLat else iLat
        val srcLon = if (lonDescending) nLon - 1 - iLon else iLon
        arr[idx] = slice.getDouble(srcLat * nLon + srcLon)
    }
}