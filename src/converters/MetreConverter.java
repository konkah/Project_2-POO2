package converters;

public class MetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
