package converters;

public class DecimetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/10;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
