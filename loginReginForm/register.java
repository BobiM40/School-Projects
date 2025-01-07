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
    private boolean isValidUser = false, isValidEmail = false;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
    public static boolean stringHas(String input, String hasWhat){
        if(hasWhat.equals("capital")){
            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) >= 'A' && input.charAt(i) <= 'Z') {
                    return true;
                }
            }
            return false;
        }
        if(hasWhat.equals("number")){
            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) >= '0' && input.charAt(i) <= '9'){
                    return true;
                }
            }
            return false;
        }
        return false;
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
                    isValidUser = false;
                }else{
                    feedback.setText(" ");
                    isValidUser = true;
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
                    isValidEmail = false;
                }else{
                    feedback.setText(" ");
                    isValidEmail = true;
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
                if(passField.getText().length() <= 8){
                    feedback.setForeground(Color.red);
                    feedback.setText("password has to be more than 8 symbols");
                }else if(!stringHas(passField.getText(), "capital")){
                    feedback.setForeground(Color.red);
                    feedback.setText("password has to include at least one capital letter");
                }else if(!stringHas(passField.getText(), "number")){
                    feedback.setForeground(Color.red);
                    feedback.setText("password has to include at least one number");
                }else if(isValidEmail && isValidUser){
                    feedback.setForeground(Color.green);
                    feedback.setText("Registration successful");
                }else if(!isValidUser){
                    feedback.setForeground(Color.red);
                    feedback.setText("username has to be more than 5 symbols");
                }else{
                    feedback.setForeground(Color.red);
                    feedback.setText("invalid email address");
                }
            }
        });
    }
}
