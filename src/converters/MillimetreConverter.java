package converters;

public class MillimetreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000;
    }
}
