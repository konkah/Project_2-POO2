package converters;

public class NanometreConverter extends AbstractConverter{
    @Override
    protected float factor() {
        return 1000000000;
    }
}
