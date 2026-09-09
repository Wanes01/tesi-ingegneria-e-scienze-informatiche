internal fun readFileAxes(dataset: NetcdfDataset, variableName: String?, file: Path): FileAxes {
    // ensures that all {time, lat, lon} axes are present
    val rawTimeAxis = requireNotNull(
        dataset.findCoordinateAxis(AxisType.Time) ?: dataset.findCoordinateAxis(AxisType.RunTime),
    ) {
        "No time axis in $file"
    }
    val latAxis = requireNotNull(dataset.findCoordinateAxis(AxisType.Lat) as? CoordinateAxis1D) {
        "No 1D latitude axis in $file"
    }
    val lonAxis = requireNotNull(dataset.findCoordinateAxis(AxisType.Lon) as? CoordinateAxis1D) {
        "No 1D longitude axis in $file"
    }
    val errMsg = Formatter()
    // constructs a CF-aware time axis
    val timeAxis = requireNotNull(CoordinateAxis1DTime.factory(dataset, rawTimeAxis, errMsg)) {
        "Cannot build time axis in $file: $errMsg"
    }
    // ...
}