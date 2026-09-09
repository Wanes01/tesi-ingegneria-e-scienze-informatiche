class MissingValueFallbackConverter<T>(
    private val delegate: MeasurementConverter<T>,
    private val defaultValue: T,
) : MeasurementConverter<T> {
    override fun convert(value: Double): T = if (value.isNaN()) defaultValue else delegate.convert(value)
    // ...
}

data class DoubleIdentityWithFallback(
    private val defaultValue: Double = Double.NaN
) : MeasurementConverter<Double> by MissingValueFallbackConverter(
    { it },
    defaultValue,
)
