open class StatelessStrategy protected constructor() {
    override fun equals(other: Any?): Boolean =
        other != null && this::class == other::class
    override fun hashCode(): Int = this::class.hashCode()
}