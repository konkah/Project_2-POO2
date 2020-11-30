package converters;

public class YardConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 9144f/10000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.DISTANCE;
    }
}
