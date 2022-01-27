/**
 * @uthor: Vinit Kumar
 * @created on: 31-Dec-2021
 * Address Book
 */
package addressBook;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class AddressBook {
    //instance variable declaration
    private ArrayList<Contact> addressBook;
    private String name;
    private final Scanner sc = new Scanner(System.in);

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
    }

    //book name getter
    public String getName() {
        return name;
    }


    //operations method
    public void operations(AddressBook book) {
        //welcome message
        System.out.println("Welcome to Address Book Program!!! " + getName());
        //initialize variable
        int option;
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