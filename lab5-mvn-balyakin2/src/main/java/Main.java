import commands.CollectionManager;


public class Main {

    public static void main(String[] args){
        System.out.println("Привет! Напиши help, чтобы узнать функционал");
        String filepath = System.getenv("javafile");
        MainApp mainapp = new MainApp(new CollectionManager(filepath));
        mainapp.start();
    }
}


