public class Main {
    public static void main(String[] args) {

        Request req = new Request();
        PinTerminal terminal = new PinTerminal();

        terminal.start(req);
    }
}