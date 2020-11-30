package converters;

public class LitreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1;
    }

    @Override
    public MeasureType type() {
        return MeasureType.VOLUME;
    }
}
