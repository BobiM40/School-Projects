import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editContact extends JFrame{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton finishEditingButton;
    private JPanel panel2;
    private JLabel feedback;

    public editContact(String phone, String firstName, String lastName){
        setSize(700, 400);
        setContentPane(panel2);
        setVisible(true);
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        finishEditingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newFirstName = firstNameField.getText();
                String newLastName = lastNameField.getText();
                if(newFirstName.isEmpty() || newLastName.isEmpty()){
                    feedback.setText("Please enter valid names! ");
                }else{
                    feedback.setText("");
                    connect.updateContact(phone, newFirstName, newLastName);
                    System.out.println("Successfully edited a contact. ");
                    phonebook.updateInfo();
                    setVisible(false);
                }
            }
        });
    }
}
