open class BidimensionalGaussianLayer<P : Position2D<P>>
@JvmOverloads
constructor(
    private val baseline: Double = 0.0,
    val centerX: Double,
    val centerY: Double,
    norm: Double,
    sigmaX: Double,
    sigmaY: Double = sigmaX,
) : Layer<Double, P>