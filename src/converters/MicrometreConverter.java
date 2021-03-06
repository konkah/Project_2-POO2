package converters;

public class MicrometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
