package converters;

public abstract class AbstractConverter {
    protected abstract float factor();
    public float toBasicUnit(float number){
        return number*factor();
    }

    public float fromBasicUnit(float number){
        return  number/factor();
    }
}
