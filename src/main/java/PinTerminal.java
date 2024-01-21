import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class PinTerminal {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel(new GridLayout(3,2));
    JLabel userIdLabel = new JLabel("Enter user id: ");
    JLabel macAddressLabel = new JLabel("Enter mac address:");
    JFormattedTextField userIdInput = new JFormattedTextField();
    JFormattedTextField macAddressInput = new JFormattedTextField();
    JTextArea resultText = new JTextArea();
    JButton submit = new JButton();

    public void start() {
        setupTerminal();
    }

//   ***
    private static void sendToOrchestrator(Request request) {
        System.out.println("API request made:");
        String apiCallString = String.format("userId: %s \nmacAdrres: %s \nresponse: %s",request.getUserId(), request.getMacAddress(), request.getResponseBody());
        System.out.println(apiCallString);
    }

// Check if input is empty or contains placeholder characters
    private static boolean validateInput(String userId, String macAddress) {
        return (!userId.contains("_") && !userId.isEmpty()) && (!macAddress.contains("_") && !macAddress.isEmpty());
    }

    private void submit() throws IOException, InterruptedException {
        String userId = userIdInput.getValue().toString();
        String macAddress = macAddressInput.getValue().toString();

        if (validateInput(userId, macAddress)) {
            Request request = new Request(userId, macAddress);
            request.makeRequest();

            String response = request.getResponseBody();
            sendToOrchestrator(request);

            if(response.startsWith("Status:")){
                resultText.setText(response);
            } else {
                resultText.setText("Not in WireMock Stub");
            }
        } else {
            resultText.setText("invalid input");
        }
        setInputFields();
    }

    private void setupTerminal() {
        setInputFields();

        submit.setText("submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    submit();
                } catch (IOException | InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.add(userIdLabel);
        panel.add(userIdInput);
        panel.add(macAddressLabel);
        panel.add(macAddressInput);
        panel.add(submit);
        panel.add(resultText);
        frame.add(panel);
    }

    private void setInputFields(){
        try {
            userIdInput.setValue("");
            MaskFormatter userIdFormatter = new MaskFormatter("#####");
            userIdFormatter.setPlaceholderCharacter('_');
            userIdFormatter.install(userIdInput);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            macAddressInput.setValue("");
            MaskFormatter macAddressFormatter = new MaskFormatter("HH:HH:HH:HH:HH:HH");
            macAddressFormatter.setPlaceholderCharacter('_');
            macAddressFormatter.install(macAddressInput);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}