package commands;

import routes.*;

import java.util.*;
import java.io.*;

/**
 * Класс выполнения команд, работы с коллекцией
 *
 * @author Maxim Balyakin
 * @version 1.0
 */

public class CollectionManager {
    private LinkedHashSet<Route> routes;
    protected static HashMap<String, String> commands;
    private final Date initiazitionDate;
    final Script script = new Script();
    File workFile = null;


    {
        commands = new HashMap<>();
        this.initiazitionDate = new Date();
        commands.put("help", "Вывести справку по доступным командам.");
        commands.put("info", "Вывести в стандартный поток вывода информацию о коллекции.");
        commands.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commands.put("add {element}", "Добавить новый элемент в коллекцию.");
        commands.put("update id {element}", "Обновить значение элемента коллекции, id которого равен заданному.");
        commands.put("remove_by_id id", "Удалить элемент из коллекции по его id.");
        commands.put("clear", "Очистить коллекцию.");
        commands.put("save", "Сохранить коллекцию в файл.");
        commands.put("execute_script file_name", "Считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же.");
        commands.put("exit", "Завершить программу (без сохранения в файл).");
        commands.put("add_if_min {element}", "Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.");
        commands.put("remove_lower {element}", "Удалить из коллекции все элементы, превышающие заданный.");
        commands.put("history", "Вывести последние 5 команд.");
        commands.put("max_by_id", "Вывести любой объект из коллекции, значение поля id которого является максимальным.");
        commands.put("print_field_descending_distance", "Вывести значения поля type всех элементов в порядке убывания.");
    }

    public CollectionManager(String filepath){
        while (true){
            File file = null;
            try {
                file = new File(filepath);
                if (file.isFile()){
                    Read collection = new Read();
                    routes = collection.reading(file);
                    workFile = file;
                    break;
                }
                throw new FileNotFoundException();
            }
            catch (FileNotFoundException e){
                if (!file.exists()){
                    System.out.println("Файл по указанному пути не существует");
                }else if (!file.canRead()){
                    System.out.println("У файла по указанному пути нет прав на чтение");
                }else if (file.isDirectory()){
                    System.out.println("Указанный путь не ведет к файлу");
                }
                System.out.println("Введите путь к файлу:");
                filepath = read();
            }
        }

    }

    public String read(){
        try{
            Scanner scan = new Scanner(System.in);
            return scan.nextLine();
        }catch (NoSuchElementException e){
            System.exit(1);
        }
        return null;
    }

    public void help(){
        System.out.println("Команды: ");
        for (String s: commands.keySet()){
            System.out.println(s + " - " + commands.get(s));
        }
    }

    public void info(){
        System.out.println("Тип коллекции - " + routes.getClass());
        System.out.println("Дата инициализации - " + initiazitionDate);
        System.out.println("Количество элементов - " + routes.size());
    }

    public void show(){
        if (routes.isEmpty()){
            System.out.println("Коллекция пуста.");
        }
        else{
            for (Route route: routes){
                System.out.println(route.toString());
            }
        }
    }

    public Route getNewRoute(){
        int id = (int) (Math.random() * 10000);
        String addName;
        float addCoordX;
        double addCoordY;
        long addLocationFromX;
        long addLocationFromY;
        float addLocationFromZ;
        String addLocationFromName;
        long addLocationToX;
        long addLocationToY;
        float addLocationToZ;
        String addLocationToName;
        long addDistance;

        while (true){
            System.out.println("Введите имя пути: ");
            addName = read();
            if (addName.equals("")){
                System.out.println("Вы ввели пустую строку, попробуйте снова.");
                continue;
            }
            break;
        }

        while (true){
            System.out.println("Введите координату x (>-781): ");
            try{
                addCoordX = Float.parseFloat(read());
                if (addCoordX <= -781){
                    System.out.println("Значение должно быть больше -781");
                    continue;
                }
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите координату y: ");
            try{
                addCoordY = Double.parseDouble(read());
                if (addCoordY > 858){
                    System.out.println("Максимальное значение поля 858");
                    continue;
                }
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите координату x начальной точки: ");
            try{
                addLocationFromX = Long.parseLong(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите координату y начальной точки: ");
            try{
                addLocationFromY = Long.parseLong(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }
        while (true){
            System.out.println("Введите координату z начальной точки: ");
            try{
                addLocationFromZ = Float.parseFloat(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите имя начальной точки: ");
            addLocationFromName = read();
            if (addLocationFromName.equals("")){
                System.out.println("Вы ввели пустую строку, попробуйте снова.");
                continue;
            }
            break;
        }

        while (true){
            System.out.println("Введите координату x конечной точки: ");
            try{
                addLocationToX = Long.parseLong(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите координату y конечной точки: ");
            try{
                addLocationToY = Long.parseLong(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }
        while (true){
            System.out.println("Введите координату z конечной точки: ");
            try{
                addLocationToZ = Float.parseFloat(read());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        while (true){
            System.out.println("Введите имя конечной точки: ");
            addLocationToName = read();
            if (addLocationToName.equals("")){
                System.out.println("Вы ввели пустую строку, попробуйте снова.");
                continue;
            }
            break;
        }


        while (true){
            System.out.println("Введите протяженность пути: ");
            try{
                addDistance = Long.parseLong(read());
                if (addDistance <= 1){
                    System.out.println("Протяженность должна быть больше 1");
                    continue;
                }
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }

        Route addRoute = new Route(id, addName, new Coordinates(addCoordX, addCoordY), new Location(addLocationFromX, addLocationFromY, addLocationFromZ, addLocationFromName), new Location(addLocationToX, addLocationToY, addLocationToZ, addLocationToName), addDistance);
        return addRoute;
    }

    public void add(){
        Route newRoute = getNewRoute();
        routes.add(newRoute);
        System.out.println("Путь успешно добавлен.");
    }

    public void update(long id){
        boolean flag = false;
        LinkedHashSet<Route> oldRoute = new LinkedHashSet<>(routes);
        for (Route el: oldRoute){
            if (el.getId() == id){
                flag = true;
                Route updatedRoute = getNewRoute();
                el.setName(updatedRoute.getName());
                el.setCoordinates(updatedRoute.getCoordinates());
                el.setTo(updatedRoute.getTo());
                el.setFrom(updatedRoute.getFrom());
                el.setDistance(updatedRoute.getDistance());
            }
        }
        if (flag){
            System.out.println("Путь успешно обновлен.");
        }else{
            System.out.println("Не существует пути с id " + id);
        }
    }

    public void remove_by_id(long id){
        boolean flag = false;
        LinkedHashSet<Route> oldRoute = new LinkedHashSet<>(routes);
        for (Route el: oldRoute){
            if (el.getId() == id){
                flag = true;
                routes.remove(el);
            }
        }
        if (flag) {
            System.out.println("Путь с id " + id + " успешно удален.");
        }else{
            System.out.println("Не существует пути с id " + id);
        }
    }

    public void clear(){
        if (routes.isEmpty()){
            System.out.println("Коллекция уже пуста.");
        }
        else{
            routes.clear();
            System.out.println("Коллекция очищена.");
        }
    }

    public void save(){
        StringBuilder toXmlFile = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        for (Route el : routes){
            toXmlFile.append("<route>\n");
            toXmlFile.append(el.toXmlFormat());
            toXmlFile.append("</route>\n");
        }

        try {
            FileWriter fileWriter = new FileWriter(workFile);
            fileWriter.write(toXmlFile.toString());
            fileWriter.flush();
            System.out.println("Коллекция успешно сохранена.");
        } catch (IOException e) {
            if (!workFile.canWrite()) {
                System.out.println("У файла нет доступа на запись.");
            }
            System.out.println("Не удалось сохранить коллекцию в файл.");
        }
    }

    public void execute_script(String filepath, CollectionManager manager){
        System.out.println("Запуск скрипта из файла: " + filepath);
        script.Scripting(filepath, manager);
    }

    public void add_if_min(){
        Route newRoute = getNewRoute();
        long minDistance = 999999999;
        for (Route el: routes){
            if (el.getDistance() < minDistance){
                minDistance = el.getDistance();
            }
        }
        if (newRoute.getDistance() < minDistance){
            routes.add(newRoute);
            System.out.println("Путь успешно добавлен.");
        }
        else {
            System.out.println("Путь не был добавлен.");
        }
    }

    public void remove_lower(){
        Route newRoute = getNewRoute();
        long lowerDistance = newRoute.getDistance();
        LinkedList<String> removed = new LinkedList<>();
        LinkedHashSet<Route> oldRoute = new LinkedHashSet<>(routes);
        for (Route el : oldRoute) {
            if (el.getDistance() < lowerDistance){
                removed.add(el.getName());
                routes.remove(el);
            }
        }
        System.out.println("Удалены пути: " + removed);
    }

    public void max_by_id(){
        int max_id = Integer.valueOf(0);
        Route result = null;
        for (Route el : routes){
            if (el.getId() > max_id) {
                max_id = el.getId();
                result = el;
            }
        }
        System.out.println("Путь с максимальным id - " + result);
    }

    public void print_field_descending_distance(){
        boolean flag = true;
        TreeSet<Route> sortedSet = new TreeSet<>(routes);
        for (Route el : sortedSet){
            flag = false;
            System.out.println(el);
        }
        if (flag){
            System.out.println("Извините, таких элементов нет");
        }
    }

}
