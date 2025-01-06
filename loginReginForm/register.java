import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class register extends JFrame{
    private JCheckBox agreeToTermsAndCheckBox;
    private JTextField userField;
    private JTextField emailField;
    private JPasswordField passField;
    private JButton loginButton;
    private JLabel user;
    private JLabel email;
    private JLabel pass;
    private JLabel feedback;
    private JPanel panel;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    public register() {
        setSize(500,500);
        setContentPane(panel);
        setVisible(true);
        loginButton.setEnabled(false);
        userField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(userField.getText().length() <= 5){
                    feedback.setForeground(Color.red);
                    feedback.setText("username has to be more than 5 symbols");
                }else{
                    feedback.setText(" ");
                }
            }
        });
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(!validate(emailField.getText())){
                    feedback.setForeground(Color.red);
                    feedback.setText("invalid email address");
                }else{
                    feedback.setText(" ");
                }
            }
        });
        agreeToTermsAndCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                loginButton.setEnabled(agreeToTermsAndCheckBox.isSelected());
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(passField.getText().equals("password")){
                    feedback.setForeground(Color.green);
                    feedback.setText("Login successful");
                }else{
                    feedback.setForeground(Color.red);
                    feedback.setText("incorrect password");
                }
            }
        });
    }
}
