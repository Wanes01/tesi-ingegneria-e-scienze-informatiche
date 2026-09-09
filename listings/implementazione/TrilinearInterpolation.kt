class TrilinearInterpolation :
    StatelessStrategy(),
    SpatioTemporalInterpolation by SeparableSpatioTemporalInterpolation(
        BilinearInterpolation(),
        LinearInterpolation(),
    )