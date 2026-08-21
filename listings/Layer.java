@FunctionalInterface
public interface Layer<T, P extends Position<? extends P>> extends Serializable {
    T getValue(P p);
}