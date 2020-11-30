package converters;

public class NanometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000000000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
