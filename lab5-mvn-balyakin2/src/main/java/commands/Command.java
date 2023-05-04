package commands;

import exceptions.UnknownCommand;

import java.util.LinkedList;

/**
 * Класс определения команды по введенной строке и ее передачи на выполнение
 *
 * @author Maxim Balyakin
 * @version 1.0
 */

public class Command {
    private LinkedList<String> lastCommands;
    {
        lastCommands = new LinkedList<>();
    }
    public void Command(String[] userCommand, CollectionManager manager) throws UnknownCommand {
        try {
            if (lastCommands.size() == 6) {
                lastCommands.removeFirst();
            }
            lastCommands.addLast(userCommand[0]);
            switch (userCommand[0]) {
                case "help":
                    manager.help();
                    break;
                case "info":
                    manager.info();
                    break;
                case "show":
                    manager.show();
                    break;
                case "add":
                    manager.add();
                    break;
                case "update":
                    manager.update(Long.parseLong(userCommand[1]));
                    break;
                case "remove_by_id":
                    manager.remove_by_id(Long.parseLong(userCommand[1]));
                    break;
                case "clear":
                    manager.clear();
                    break;
                case "save":
                    manager.save();
                    break;
                case "execute_script":
                    manager.execute_script(userCommand[1], manager);
                    break;
                case "exit":
                    System.out.println("Завершние программы...");
                    System.exit(1);
                case "add_if_min":
                    manager.add_if_min();
                    break;
                case "remove_lower":
                    manager.remove_lower();
                    break;
                case "history":
                    lastCommands.removeLast();
                    System.out.println(lastCommands);
                    break;
                case "max_by_id":
                    manager.max_by_id();
                    break;
                case "print_field_descending_distance":
                    manager.print_field_descending_distance();
                    break;
                default:
                    lastCommands.removeLast();
                    throw new UnknownCommand(userCommand[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Отсутствует аргумент. Наберите 'help' для справки.");
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели не число. Наберите 'help' для справки.");
        }
    }
}
