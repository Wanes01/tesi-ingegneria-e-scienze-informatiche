internal fun resolveVariable(
    ds: NetcdfDataset,
    name: String?,
    timeDimName: String,
    latDimName: String,
    lonDimName: String,
    file: Path,
): Variable {
    if (name != null) {
        return requireNotNull(ds.findVariable(name)) {
            "Variable '$name' not found in $file. " +
                "Available: ${ds.variables.map { it.shortName }}"
        }
    }
    val targetDims = setOf(timeDimName, latDimName, lonDimName)
    // 3D variables matching {latitude, longitude, time}
    val candidates = ds.variables.filter { v ->
        v.dimensions.size == 3 &&
            v.dimensions.map { it.name }.toSet() == targetDims
    }
    require(candidates.isNotEmpty()) {
        "No variable with dimensions $targetDims found in $file"
    }
    require(candidates.size == 1) {
        "Multiple candidate variables with dimensions $targetDims in $file. " +
            "The variables that can be used are: ${candidates.map { it.shortName }}. Specify the variable explicitly."
    }
    return candidates.single()
}