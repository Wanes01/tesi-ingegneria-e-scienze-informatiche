init {
    // maps all file time instances to the corresponding RasterGrid, sorting them by Instant
    val map = TreeMap<Instant, RasterGrid>()
    // spatial grid and variable are established by the first file, validated against all the others
    var reference: ReferenceGrid? = null
    for (file in listDataFiles(directory)) {
        openNetcdfDataset(file).use { ds ->
            val axes = readFileAxes(ds, variableName, file)
            reference = reference?.also { it.requireMatches(axes, file, directory) } ?: ReferenceGrid(axes)
            readTimestepsFrom(map, axes, directory)
        }
    }
    instants = map.keys.toList()
    grids = map.values.toList()
}