package converters;

public class DecametreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 10;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
