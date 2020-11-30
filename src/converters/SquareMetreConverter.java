package converters;

public class SquareMetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1;
    }

    @Override
    public MeasureType type() {
        return MeasureType.AREA;
    }
}
