package converters;

public class CubicCentimetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000;
    }
}
