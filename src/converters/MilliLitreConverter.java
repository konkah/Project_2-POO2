package converters;

public class MilliLitreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.VOLUME;
    }
}
