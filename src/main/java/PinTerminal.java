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
    Request request;

    public PinTerminal(Request req) {
        request = req;
    }
    public void start() {
        generateTerminal();
    }

    private static boolean validate(String userId, String macAddress) {
        return (!userId.contains("_") && !userId.isEmpty()) && (!macAddress.contains("_") && !macAddress.isEmpty());
    }

    private void submit() throws IOException, InterruptedException {
        String userId = userIdInput.getValue().toString();
        String macAddress = macAddressInput.getValue().toString();

        if (validate(userId, macAddress)) {
            request.setUserId(userId);
            request.setMacAddress(macAddress);
            request.makeRequest();
            resultText.setText(request.getResponseBody());
        } else {
            resultText.setText("invalid input");
        }
        setInputFields();
    }

    private void generateTerminal() {
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