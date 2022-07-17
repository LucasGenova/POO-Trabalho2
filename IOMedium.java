import java.util.ArrayList;

public interface IOMedium {
    void clearConsole();
    void panik(String error); //Display an error message.
    void display(String message); //Display a message.
    void init(); //Initial screen of the application.
    String [] loginScreen(); //Login screen. Returns username, password pair.
    int optionMenu(String title, String empty,  String [] options); //Propts menu for user and returns the option chosen or -1 to quit
    <T> void displayList(ArrayList<T> list); //Mostra uma ArrayList
    int getInt(String title, String error); //Ask for an int until it's given by the user
    double getDouble(String title, String error); //Ask for a double until it's given by the user
    String getString(String title, String error); //Ask for a String until it's given by the user
    Date getDate(String title, String error); //Ask for a Date until it's given by the user
}