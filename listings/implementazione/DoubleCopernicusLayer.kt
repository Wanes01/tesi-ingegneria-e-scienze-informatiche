class DoubleCopernicusLayer : CopernicusLayer<Double> {

    constructor(
        environment: Environment<*, GeoPosition>,
        data: GridSnapshots,
        timeScale: Duration = DEFAULT_TIME_SCALE,
        timeOrigin: Instant? = null,
        interpolation: SpatioTemporalInterpolation = TrilinearInterpolation(),
        converter: MeasurementConverter<Double> = DoubleIdentityWithFallback(),
    ) : super( /* ... */ )

    @JvmOverloads
    constructor(
        environment: Environment<*, GeoPosition>,
        dataDirectory: String,
        timeScale: String = DEFAULT_TIME_SCALE_ISO,
        timeOrigin: String? = null,
        variable: String? = null,
        interpolation: SpatioTemporalInterpolation = TrilinearInterpolation(),
        converter: MeasurementConverter<Double> = DoubleIdentityWithFallback(),
    ) : super( /* ... */ )

    @JvmOverloads
    constructor(
        environment: Environment<*, GeoPosition>,
        endpoint: String,
        dataset: String,
        inputsFile: String,
        checkMd5: Boolean,
        timeScale: String = DEFAULT_TIME_SCALE_ISO,
        timeOrigin: String? = null,
        variable: String? = null,
        interpolation: SpatioTemporalInterpolation = TrilinearInterpolation(),
        converter: MeasurementConverter<Double> = DoubleIdentityWithFallback(),
        cacheDirectory: String = DEFAULT_CACHE_DIRECTORY,
        cdsApiRcFile: String = CdsApiRc.DEFAULT_LOCATION.toString(),
        providerTimeout: String = CopernicusDataStoreProvider.DEFAULT_TIMEOUT.toIsoString(),
    ) : super( /* ... */ )

    // ...
}
