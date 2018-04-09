package JavaClasses;

public class Facade {

    private final static Management management = new Management();

    public static String selectPersons(){
        return management.SelectPersons();
    }

    public static String ConnectToTheServer(String database, String host, String port, String user, String password){
        return management.ConnectToTheServer(database,host,port,user,password);
    }
    public static void closeConnection(){
        management.closeConnection();
    }

}
