package converters;

public class HectometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 100;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
