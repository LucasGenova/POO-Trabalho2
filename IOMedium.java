import java.util.ArrayList;

public interface IOMedium {
    void clearConsole();
    void panik(String error); //Display an error message.
    void display(String message); //Display a message.
    void init(); //Initial screen of the application.
    String [] loginScreen(); //Login screen. Returns username, password pair.
    int optionMenu(String title, String empty,  String [] options); //Propts menu for user and returns the option chosen or -1 to quit
    <T> void displayList(ArrayList<T> list); //Mostra uma ArrayList
}