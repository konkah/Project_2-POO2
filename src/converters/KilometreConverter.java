package converters;

public class KilometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1f/1000;
    }
}
