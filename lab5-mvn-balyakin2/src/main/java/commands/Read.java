package commands;

import routes.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Класс чтения и парсинга данных из файла формата xml
 *
 * @author Maxim Balyakin
 * @version 1.0
 */

public class Read {
    private String finalXmlString = "";
    private LinkedHashSet<Route> routes = new LinkedHashSet<>();

    public LinkedHashSet<Route> reading(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder xmlStringAll = new StringBuilder();
        while (scan.hasNextLine()) {
            xmlStringAll.append(scan.nextLine()).append("\n");
        }

        String[] routesList = xmlStringAll.toString().split("</route>");
        for (int temp = 0; temp < routesList.length - 1; temp++) {

            this.finalXmlString = routesList[temp];

            int id = Integer.parseInt(splitter("id"));
            String name = splitter("name");
            float coordinates_x = Float.parseFloat(splitter("coord-x"));
            Double coordinates_y = Double.parseDouble(splitter("coord-y"));
            java.util.Date creationDate = null;
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                creationDate = formatter.parse(splitter("creationDate"));
            } catch (ParseException e) {
                System.out.println("Не удалось прочитать файл");
            }
            Long loc_from_x = Long.parseLong(splitter("loc-from-x"));
            long loc_from_y = Long.parseLong(splitter("loc-from-y"));
            float loc_from_z = Float.parseFloat(splitter("loc-from-z"));
            String loc_from_name = splitter("loc-from-name");
            Long loc_to_x = Long.parseLong(splitter("loc-to-x"));
            long loc_to_y = Long.parseLong(splitter("loc-to-y"));
            float loc_to_z = Float.parseFloat(splitter("loc-to-z"));
            String loc_to_name = splitter("loc-to-name");
            long distance = Long.parseLong(splitter("distance"));

            Route route = new Route(id, name, new Coordinates(coordinates_x, coordinates_y), creationDate, new Location(loc_from_x, loc_from_y, loc_from_z, loc_from_name), new Location(loc_to_x, loc_to_y, loc_to_z, loc_to_name), distance);
            routes.add(route);
            System.out.println("Путь " + name + " успешно добавлен.");

        }
        return routes;
    }

    /**
     * Метод получения данных по тегу
     *
     * @param tag - xml тег, данные которого нужно получить
     */

    public String splitter(String tag){
        String str = finalXmlString.split(tag)[1];
        str = str.substring(1, str.length() - 2).trim();
        return str;
    }

}
