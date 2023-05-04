package routes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Route implements Comparable<Route>{
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDateString;
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле не может быть null
    private Location to; //Поле не может быть null
    private long distance; //Значение поля должно быть больше 1

    public Route(int id, String name, Coordinates coordinates, Location from, Location to, long distance){
        super();
        this.id = id;
        this.name = name;
        this.creationDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.creationDateString = formatter.format(this.creationDate);
        this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(int id, String name, Coordinates coordinates, java.util.Date creationDate, Location from, Location to, long distance){
        super();
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.creationDateString = formatter.format(creationDate);
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }


    public String toXmlFormat(){
        String xmlString = "";

        xmlString += String.format("<id>%d</id>\n", this.id);
        xmlString += String.format("<name>%s</name>\n", this.name);
        xmlString += String.format("<coord-x>%s</coord-x>\n", this.coordinates.getX());
        xmlString += String.format("<coord-y>%s</coord-y>\n", this.coordinates.getY());
        xmlString += String.format("<creationDate>%s</creationDate>\n", this.creationDateString);
        xmlString += String.format("<loc-from-x>%s</loc-from-x>\n", this.from.getX());
        xmlString += String.format("<loc-from-y>%s</loc-from-y>\n", this.from.getY());
        xmlString += String.format("<loc-from-z>%s</loc-from-z>\n", this.from.getZ());
        xmlString += String.format("<loc-from-name>%s</loc-from-name>\n", this.from.getName());
        xmlString += String.format("<loc-to-x>%s</loc-to-x>\n", this.to.getX());
        xmlString += String.format("<loc-to-y>%s</loc-to-y>\n", this.to.getY());
        xmlString += String.format("<loc-to-z>%s</loc-to-z>\n", this.to.getZ());
        xmlString += String.format("<loc-to-name>%s</loc-to-name>\n", this.to.getName());
        xmlString += String.format("<distance>%s</distance>\n", this.distance);

        return xmlString;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDateString +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Route o) {
        return ((int) this.distance - (int) o.getDistance());
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public long getDistance() {
        return distance;
    }
}

