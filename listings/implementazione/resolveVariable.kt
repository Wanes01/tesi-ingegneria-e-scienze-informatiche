internal fun resolveVariable(
    ds: NetcdfDataset,
    name: String?,
    timeDimName: String,
    latDimName: String,
    lonDimName: String,
    // ...
): Variable {
    if (name != null) {
        return requireNotNull(ds.findVariable(name)) { /* ... */}
    }
    val targetDims = setOf(timeDimName, latDimName, lonDimName)
    // 3D variables matching {latitude, longitude, time}
    val candidates = ds.variables.filter { v ->
        v.dimensions.size == 3 &&
            v.dimensions.map { it.name }.toSet() == targetDims
    }
    require(candidates.isNotEmpty()) { /* ... */ }
    require(candidates.size == 1) { /* ... */ }
    return candidates.single()
}