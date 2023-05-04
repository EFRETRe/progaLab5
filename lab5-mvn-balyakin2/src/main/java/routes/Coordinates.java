package routes;

public class Coordinates {
    private float x; //Значение поля должно быть больше -781
    private Double y; //Максимальное значение поля: 858, Поле не может быть null

    public Coordinates(float x, Double y){
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public Double getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}