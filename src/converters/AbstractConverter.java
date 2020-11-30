package converters;

public abstract class AbstractConverter implements Comparable {
    protected abstract float factor();

    public float toBasicUnit(float number){
        return number*factor();
    }

    public float fromBasicUnit(float number){
        return number/factor();
    }

    public abstract MeasureType type();

    @Override
    public String toString() {
        String type = this.type().getTypeString();
        return name() + " [" + type + "]";
    }

    private String name() {
        return getClass().getSimpleName()
                .replace("Converter", "")
                .toLowerCase();
    }

    @Override
    public int compareTo(Object other) {
        String name = other instanceof AbstractConverter
            ? ((AbstractConverter) other).name()
            : "";

        return name().compareTo(name);
    }
}
