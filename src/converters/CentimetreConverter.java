package converters;

public class CentimetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/100;
    }
}
