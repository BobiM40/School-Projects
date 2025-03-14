import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class addContact extends JFrame{
    private JLabel imgLabel;
    private JPanel panel1;
    private JTextField phoneField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton addContactButton;
    private JLabel feedback;
    private JButton openFromFilesButton;
    public addContact(){
        setSize(700, 400);
        setContentPane(panel1);
        setVisible(true);
        ImageIcon ogIcon = new ImageIcon("/home/bmarkov25/IdeaProjects/phonebookWithDB/src/default-avatar-icon-of-social-media-user-vector.jpg");
        Image image = ogIcon.getImage();
        Image scaledImg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        imgLabel.setIcon(scaledIcon);
        openFromFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser window for selecting a file
                JFileChooser fileChooser = new JFileChooser();

                // Show the open dialog to the user and store the result (APPROVE_OPTION if a file is selected)
                int result = fileChooser.showOpenDialog(null);

                // If the user selects a file, proceed with loading and displaying the image
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Read the image from the selected file
                        Image image = ImageIO.read(selectedFile);

                        // Scale the image to 100x100 pixels and create an ImageIcon
                        ImageIcon icon = new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

                        // Set the ImageIcon to the label and clear any existing text
                        imgLabel.setIcon(icon);
                        imgLabel.setText("");
                    } catch (IOException ex) {
                        // Show an error message if there is an issue loading the image
                        JOptionPane.showMessageDialog(null, "Error loading image.");
                    }
                }
            }
        });
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                boolean phoneValid = true;
                if(phone.isEmpty()){
                    phoneValid = false;
                }else if(((phone.charAt(0) < '0' || phone.charAt(0) > '9') && phone.charAt(0) != '+') || phone.length() < 8){
                    phoneValid = false;
                }else {
                    for (int i = 1; i < phone.length(); i++) {
                        if(phone.charAt(i) < '0' || phone.charAt(i) > '9'){
                            phoneValid = false;
                            break;
                        }
                    }
                }
                if(phoneValid){
                    if(firstName.isEmpty() || lastName.isEmpty()){
                        feedback.setText("Fill in the names of the contact! ");
                    }else{
                        feedback.setText("");
                        // Convert the icon from JLabel to an InputStream (for image storage in the DB)
                        ImageIcon icon = (ImageIcon) imgLabel.getIcon();
                        InputStream imageInputStream = null;

                        if (icon != null) {
                            // Create a BufferedImage to hold the icon
                            BufferedImage bufferedImage = new BufferedImage(
                                    icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

                            // Create a Graphics object to draw the icon onto the BufferedImage
                            Graphics g = bufferedImage.createGraphics();
                            icon.paintIcon(null, g, 0, 0);
                            g.dispose();  // Dispose of the Graphics object once done

                            // Convert the BufferedImage to an InputStream
                            try {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                                imageInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            } catch (IOException ex) {
                                // Show an error message if there's an issue converting the image to a binary stream
                                JOptionPane.showMessageDialog(null, "Error converting image to binary stream.");
                                return;  // Exit the method if there's an error
                            }
                        }
                        connect.addContact(phone, firstName, lastName, imageInputStream);
                        System.out.println("Successfully added a new contact. ");
                        phonebook.updateInfo();
                        setVisible(false);
                    }
                }else{
                    feedback.setText("Enter a valid phone number! ");
                }
            }
        });
    }
}
