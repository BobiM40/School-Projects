import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class phonebook extends JFrame{
    private JPanel panel;
    private JTable table1;
    private JButton addContactButton;
    private JButton deleteContactButton;
    private JButton updateContactButton;
    public static DefaultTableModel model; // Table model to manage data dynamically
    private static ArrayList<String[]> contacts;
    private int selectedRow;
    public phonebook(){
        setSize(700, 400);
        setContentPane(panel);
        setVisible(true);

        updateContactButton.setEnabled(false);
        deleteContactButton.setEnabled(false);
        // Initialize the table model with column names
        model = new DefaultTableModel();
        table1.setModel(model);

        // Execute initial database query to fetch client data
        contacts = connect.executeQuery("SELECT phone, first_name, last_name FROM phonebook.phones");
        // Populate the table with the retrieved data
        updateTable();
        addContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new addContact();
            }
        });
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    selectedRow = table1.getSelectedRow();
                    if(selectedRow == -1){
                        updateContactButton.setEnabled(false);
                        deleteContactButton.setEnabled(false);
                    }else{
                        updateContactButton.setEnabled(true);
                        deleteContactButton.setEnabled(true);
                    }
                }
            }
        });
        updateContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new editContact((String)table1.getValueAt(selectedRow, 0), (String)table1.getValueAt(selectedRow, 1), (String)table1.getValueAt(selectedRow, 2));
                updateContactButton.setEnabled(false);
                deleteContactButton.setEnabled(false);
            }
        });
        deleteContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect.deleteContact((String)table1.getValueAt(selectedRow, 0));
                updateInfo();
                updateContactButton.setEnabled(false);
                deleteContactButton.setEnabled(false);
            }
        });
    }
    public static void updateInfo(){
        model.setColumnCount(0);
        contacts = connect.executeQuery("SELECT phone, first_name, last_name FROM phonebook.phones");
        updateTable();
    }
    private static void updateTable() {
        model.setRowCount(0); // Clear all existing rows in the table
        for (String[] contact : contacts) {
            model.addRow(contact); // Add each client's data as a new row
        }
    }
}
