public class Main {
    public static void main(String[] args) {

        Request req = new Request();
        PinTerminal terminal = new PinTerminal(req);

        terminal.start();
    }
}