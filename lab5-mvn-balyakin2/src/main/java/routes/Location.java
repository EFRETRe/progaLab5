package routes;

public class Location {
    private Long x; //Поле не может быть null
    private long y;
    private float z;
    private String name; //Строка не может быть пустой, Поле может быть null

    public Location(Long x, long y, float z, String name){
        super();
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + "," + z + "," + name + ")";
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

}