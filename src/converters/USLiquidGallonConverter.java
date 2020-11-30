package converters;

public class USLiquidGallonConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 378541f/100000;
    }

    @Override
    public MeasureType type() {
        return MeasureType.VOLUME;
    }
}
