/**
 * @uthor: Vinit Kumar
 * @created on: 31-Dec-2021
 * Address Book
 */
package addressBook;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class AddressBook {
    //instance variable declaration
    private ArrayList<Contact> addressBook;
    private String name;
    private final Scanner sc = new Scanner(System.in);
    public static String address_fileName = "O:\\Bridge\\JAVA\\AddressBook\\resources\\address-book.txt";
    public static String address_fileNameCSV="O:\\Bridge\\JAVA\\AddressBook\\resources\\address-bookcsv.csv";
    public static String address_fileNameJSON="O:\\Bridge\\JAVA\\AddressBook\\resources\\address-bookjson.json";



    //constructor
    AddressBook(String name) {
        this.addressBook = new ArrayList<Contact>();
        this.name = name;
    }

    /**
     * method to print address book
     * iterate through book if there is any contact
     * print contact
     */
    public void displayContact() {
        if (addressBook.size() > 0) {
            for (int i = 0; i < addressBook.size(); i++) {
                System.out.println();
                System.out.println(addressBook.get(i));
            }
        } else {
            System.out.println("No Records Present.");
        }
        System.out.println("Read from JSON");
        try {
            JSONParser p = new JSONParser();
            Object ob=p.parse(new FileReader(address_fileNameJSON));
            JSONObject obj=(JSONObject)ob;
            System.out.println("First Name: "+ (String) obj.get("First Name"));
            System.out.println("Last Name: "+(String) obj.get("Last Name"));
            System.out.println("Address: "+(String) obj.get("Address"));
            System.out.println("City: "+(String) obj.get("City"));
            System.out.println("State: "+(String) obj.get("State"));
            System.out.println("Zip: "+(String) obj.get("Zip"));
            System.out.println("Phone: "+(String) obj.get("Phone number"));
            System.out.println("Email: "+(String) obj.get("Email"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writes addressBook to file
     * create String buffer
     * iterate through book for string buffer
     * write in file
     */
    public void writeIntoFile() {
        StringBuffer addressBookFile = new StringBuffer();
        addressBook.forEach(contact1 -> {
            String addressData = contact1.toString().concat("\n");
            addressBookFile.append(addressData);
        });
        try {
            Files.write(Paths.get(address_fileName), addressBookFile.toString().getBytes()); //Read File
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * write to csv
     * open csv
     * split data into array
     * write
     * @param csv
     */
    public void writeIntoFileSCV(String csv) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(address_fileNameCSV,true));
            String[] dataArray=csv.split("#");
            writer.writeNext(dataArray);
            writer.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void writeIntoFileJSON(String json){
        try {
            String[] dataArray = json.split("#");
            JSONObject js=new JSONObject();
            js.put("First Name",dataArray[0]);
            js.put("Last Name",dataArray[1]);
            js.put("Address",dataArray[2]);
            js.put("City",dataArray[3]);
            js.put("State",dataArray[4]);
            js.put("Zip",dataArray[5]);
            js.put("Phone number",dataArray[6]);
            js.put("Email",dataArray[7]);
            FileWriter file=new FileWriter(address_fileNameJSON);
            file.write(js.toString());
            System.out.println("Written Successfully.");
            file.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    /**
     * method displays menu
     * return option chosen by user
     */
    public int menu() {
        System.out.println(
                "Select an operation: \n1- To add contact to AddressBook\n2- To edit contact from Address Book\n3- To" +
                        " delete contact from AddressBook\n4- To view AddressBook\n5- To return");
        return sc.nextInt();
    }

    /**
     * method to add contact to Address Book
     * takes input
     * create contact
     * add contact to Address Book
     */
    public void addContact() {
        //variable declaration
        String firstName, lastName, address, city, state, email;
        int zip;
        long p_number;
        //input
        System.out.println("Enter First Name");
        firstName = sc.next();
        System.out.println("Enter Last Name");
        lastName = sc.next();
        if (personExist(firstName + lastName)) {
            System.out.println("User Exists!!!");
            return;
        }
        System.out.println("Enter Address");
        address = sc.next();
        System.out.println("Enter City");
        city = sc.next();
        System.out.println("Enter State");
        state = sc.next();
        System.out.println("Enter ZipCode");
        zip = sc.nextInt();
        System.out.println("Enter Email");
        email = sc.next();
        System.out.println("Enter Phone Number");
        p_number = sc.nextLong();
        Contact contact = new Contact(firstName, lastName, address, city, state, zip, p_number, email);
        System.out.println("Contact created!!!"); //contact created
        addressBook.add(contact);
        System.out.println("User Added Successfully!!!");// contact added
        writeIntoFile();
        String data=firstName+"#"+lastName+"#"+address+"#"+city+"#"+state+"#"+zip+"#"+p_number+"#"+email;
        writeIntoFileSCV(data);
        writeIntoFileJSON(data);
    }

    /**
     * method to edit contact in Address Book
     * takes first name if Address Book is not empty
     * show menu to choose which field needs to be change if first name exists
     * take updated value
     * Update value
     */
    public void editContact() {
        if (addressBook.size() > 0) { // check if there is any contact exits
            String name, updateValue = "";
            int update = -1, i, flag = 0;
            System.out.println("Enter first name of person you want to edit: ");
            name = sc.next(); // first name
            for (i = 0; i < addressBook.size(); i++) {
                if (addressBook.get(i).getFirstName().equalsIgnoreCase(name)) { // check if name exists
                    System.out.println(
                            "Enter field of person you want to edit: \n1. Address \n2. City \n3. State \n4. Zip \n5. " +
                                    "Phone Number \n6. Email"); // shows menu
                    update = sc.nextInt();
                    System.out.println("Enter new value: ");
                    updateValue = sc.next();// get new value
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                switch (update) { //update contact
                    case 1:
                        addressBook.get(i).setAddress(updateValue);
                        System.out.println("User Updated Successfully!!!");
                        break;
                    case 2:
                        addressBook.get(i).setCity(updateValue);
                        System.out.println("User Updated Successfully!!!");
                        break;
                    case 3:
                        addressBook.get(i).setState(updateValue);
                        System.out.println("User Updated Successfully!!!");
                        break;
                    case 4:
                        addressBook.get(i).setZip(Integer.parseInt(updateValue));
                        System.out.println("User Updated Successfully!!!");
                        break;
                    case 5:
                        addressBook.get(i).setP_number(Long.parseLong(updateValue));
                        System.out.println("User Updated Successfully!!!");
                        break;
                    case 6:
                        addressBook.get(i).setEmail(updateValue);
                        System.out.println("User Updated Successfully!!!");
                        break;
                    default:
                        System.out.println("Invalid Input!!!.");
                }
            } else {
                System.out.println("Cannot Find First Name!!!");
            }
        } else {
            System.out.println("No Records Present. Please add contact to use this functionality");
        }
        writeIntoFile();
    }

    /**
     * method to delete contact in Address Book
     * takes First Name if Address Book is not empty
     * search First Name
     * delete contact if exists
     */
    public void delContact() {
        if (addressBook.size() > 0) { // if Address Book is not Empty
            String name;
            int i, flag = 0;
            System.out.println("Enter first name of person you want to delete: ");
            name = sc.next(); // input name to delete
            for (i = 0; i < addressBook.size(); i++) {
                if (addressBook.get(i).getFirstName().equalsIgnoreCase(name)) {
                    flag = 1;
                    addressBook.remove(i); // delete contact
                    System.out.println("User Deleted Successfully!!!");
                    break;
                }
            }
            if (flag == 0) {
                System.out.println("Cannot Find First Name!!!");
            }
        } else {
            System.out.println("No Records Present. Please add contact to use this functionality");
        }
        writeIntoFile();
    }

    //book name getter
    public String getName() {
        return name;
    }

    /**
     * checks if person exists in book
     * filter contact by combination of first name and last name
     * finds if contact exits
     * else return false
     *
     * @param searchName
     * @return
     */
    public boolean personExist(String searchName) {
        Contact filterStream =
                addressBook.stream()
                        .filter(contact -> searchName.equals(contact.getFirstName() + contact.getLastName())).findAny()
                        .orElse(null);
        if (filterStream == null)
            return false;
        else
            return true;
    }

    /**
     * Search person by city
     * takes city
     * filter contact for city
     * if city matches creates a list
     * print list
     *
     * @param city
     */
    public void searchPersonByCity(String city) {
        List<Contact> streamList;
        streamList = addressBook.stream()
                .filter(contact -> city.equals(contact.getCity())).collect(Collectors.toList());
        for (Contact person : streamList) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }
    }

    /**
     * Search person by state
     * takes state
     * filter contact for state
     * if state matches creates a list
     * print list
     *
     * @param state
     */
    public void searchPersonByState(String state) {
        List<Contact> streamList;
        streamList = addressBook.stream()
                .filter(contact -> state.equals(contact.getState())).collect(Collectors.toList());
        for (Contact person : streamList) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }
    }

    /**
     * Map contacts to city
     * calls groupingBy method
     * return mapped contacts to city
     *
     * @return
     */
    public Map<String, List<Contact>> cityMap() {
        return addressBook.stream().collect(groupingBy(Contact::getCity));
    }


    /**
     * Map contacts to state
     * calls groupingBy method
     * return mapped contacts to state
     *
     * @return
     */
    public Map<String, List<Contact>> stateMap() {
        return addressBook.stream().collect(groupingBy(Contact::getState));
    }

    /**
     * Prints sorted contact for each book
     * take sortBy to check basis of sorting(Name,City,State,Zip)
     * print current address book
     * call sorted method to sort and collects list
     * print list
     *
     * @param sortBy
     */
    public void sort(String sortBy) {
        List<Contact> sortContact = new ArrayList<Contact>();
        System.out.println("\nFor book: " + getName() + "sorted by " + sortBy);
        switch (sortBy) {
            case ("name"):
                sortContact =
                        addressBook.stream().sorted(Comparator.comparing(Contact::getFirstName))
                                .collect(Collectors.toList());
                break;
            case ("city"):
                sortContact =
                        addressBook.stream().sorted(Comparator.comparing(Contact::getCity))
                                .collect(Collectors.toList());
                break;
            case ("state"):
                sortContact =
                        addressBook.stream().sorted(Comparator.comparing(Contact::getState))
                                .collect(Collectors.toList());
                break;
            case ("zip"):
                sortContact =
                        addressBook.stream().sorted(Comparator.comparing(Contact::getZip)).collect(Collectors.toList());
                break;
        }
        for (Contact contact : sortContact) {
            System.out.println(contact);
        }
    }

    //operations method
    public void operations(AddressBook book) {
        //welcome message
        System.out.println("Welcome to Address Book Program!!! " + getName());
        //initialize variable
        int option;
        writeIntoFile();
        //functionality
        while (true) {
            option = menu(); // display menu
            if (option == 1) {
                book.addContact(); // add contact
            } else if (option == 2) {
                book.editContact(); // edit contact
            } else if (option == 3) {
                book.delContact(); //delete contact
            } else if (option == 4) {
                book.displayContact(); // display contact
            } else if (option == 5) {
                System.out.println("Closed Book " + getName()); // user quits
                return;
            } else
                System.out.println("Invalid Input. Please enter a valid Input!!!! "); // invalid input
        }
    }
}