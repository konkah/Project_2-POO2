package converters;

public class SoccerFieldConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 7140;
    }

    @Override
    public MeasureType type() {
        return MeasureType.AREA;
    }
}
