public interface IOMedium {
    void panik(String error); //Display an error message.
    void display(String message); //Display a message.
    void init(); //Initial screen of the application.
    String [] loginScreen(); //Login screen. Returns username, password pair.
}