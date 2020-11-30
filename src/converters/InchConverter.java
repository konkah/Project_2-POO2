package converters;

public class InchConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 254f/10000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
