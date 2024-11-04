import java.util.*;
public class Main {
    public static void main(String[] args) {
        Dictionary<String, String> phonebook = new Hashtable<>();
        String choice = null;
        Scanner scan = new Scanner(System.in);
        boolean run = true, valid = true;
        do {
            if(valid) {
                System.out.println("What do you wish to do: ");
                System.out.println("1. Read phonebook");
                System.out.println("2. Add a contact");
                System.out.println("3. Update a contact");
                System.out.println("4. Delete a contact");
                System.out.println("5. Close the phonebook application");
            }
            choice = scan.next();
            try {
                switch (Integer.valueOf(choice)) {
                    case 1:
                        System.out.println("You have " + phonebook.size() + " contacts saved in the phonebook. ");
                        if(!phonebook.isEmpty()) {
                            System.out.println("-----------------------------");
                            for (Enumeration k = phonebook.keys(); k.hasMoreElements(); ) {
                                String key = (String) k.nextElement();
                                System.out.println(phonebook.get(key) + ": " + key);
                            }
                            System.out.println("-----------------------------");
                        }
                        valid = true;
                        break;
                    case 2:
                        System.out.println("Enter the phone number of your new contact: ");
                        String phone = scan.next();
                        if(phonebook.get(phone) != null){
                            System.out.println("A contact with that phone number already exists! ");
                            System.out.println(phone + " " + phonebook.get(phone));
                        }else {
                            System.out.println("Enter the name of your new contact: ");
                            String name = scan.next();
                            phonebook.put(phone, name);
                            System.out.println(name + " with phone number " + phone + " has been added to the phonebook. ");
                        }
                        valid = true;
                        break;
                    case 3:
                        System.out.println("Enter the phone number of the contact you wish to edit: ");
                        String oldPhone = scan.next();
                        if(phonebook.get(oldPhone) == null){
                            System.out.println("A contact with such phone number doesn't exist! ");
                        }else {
                            System.out.println("Enter the new name of your new contact: ");
                            String newName = scan.next();
                            phonebook.put(oldPhone, newName);
                            System.out.println("Phone number " + oldPhone + " now refers to " + newName);
                        }
                        valid = true;
                        break;
                    case 4:
                        System.out.println("Enter the phone of the contact you want to delete: ");
                        String delPhone = scan.next();
                        if(phonebook.get(delPhone) == null){
                            System.out.println("A contact with such number doesn't exist! ");
                        }else {
                            phonebook.remove(delPhone);
                            System.out.println("Contact with phone number " + delPhone + " has been deleted. ");
                        }
                        valid = true;
                        break;
                    case 5:
                        System.out.println("Closing application. ");
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid input, try again:");
                        valid = false;
                        break;

                }
            }catch(Exception e){
                System.out.println("Invalid input, try again:");
                valid = false;
            }
        }while(run);
    }
}
