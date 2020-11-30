package converters;

public class KilometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
