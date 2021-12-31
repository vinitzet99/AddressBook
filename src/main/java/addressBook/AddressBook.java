/**
 * @uthor: Vinit Kumar
 * @created on: 31-Dec-2021
 * Address Book
 */
package addressBook;

import java.util.ArrayList;
import java.util.Scanner;

//Contact
class Contact {
    //instance variable declaration
    private String f_name, l_name, address, city, state, email;
    private int zip;
    long p_number;

    //constructor
    Contact(String f_name, String l_name, String address, String city, String state, int zip, long p_number,
            String email) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.p_number = p_number;
        this.email = email;

    }

    //getters and setters
    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getAddress() {
        return address;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setP_number(long p_number) {
        this.p_number = p_number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public int getZip() {
        return zip;
    }

    public long getP_number() {
        return p_number;
    }

    public String toString() {
        return (" Name: " + this.getF_name() + " " + this.getL_name() + "\n Address: " + this.getAddress() + " ," +
                this.getCity() + " ," + this.getState() + " ," + this.getZip() + "\n Contact: " + this.getP_number() +
                "\n Email: " + this.getEmail());
    }

}


public class AddressBook {
    //instance variable declaration
    public static ArrayList<Contact> addressBook = new ArrayList<Contact>();
    public static final Scanner sc = new Scanner(System.in);

    /**
     * method to print address book
     * iterate through book if there is any contact
     * print contact
     */
    public static void displayContact() {
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
    public static int menu() {
        System.out.println(
                "Select an operation: \n1- To add contact to AddressBook\n2- To edit contact from Address Book\n3- To view AddressBook\n4- To quit");
        return sc.nextInt();
    }

    /**
     * method to add contact to Address Book
     * takes input
     * create contact
     * add contact to Address Book
     */
    public static void addContact() {
        //variable declaration
        String f_name, l_name, address, city, state, email;
        int zip;
        long p_number;
        //input
        System.out.println("Enter First Name");
        f_name = sc.next();
        System.out.println("Enter Last Name");
        l_name = sc.next();
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
        Contact contact = new Contact(f_name, l_name, address, city, state, zip, p_number, email);
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
    public static void editContact() {
        if (addressBook.size() > 0) { // check if there is any contact exits
            String name, updateValue = "";
            int update = -1, i, flag = 0;
            System.out.println("Enter first name of person you want to edit: ");
            name = sc.next(); // first name
            for (i = 0; i < addressBook.size(); i++) {
                if (addressBook.get(i).getF_name().equalsIgnoreCase(name)) { // check if name exists
                    System.out.println(
                            "Enter field of person you want to edit: \n1. Address \n2. City \n3. State \n4. Zip \n5. Phone Number \n6. Email"); // shows menu
                    update = sc.nextInt();
                    System.out.println("Enter new value: ");
                    updateValue = sc.next();// get new value
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                switch (update) {
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

    //main method
    public static void main(String[] args) {
        //welcome message
        System.out.println("Welcome to Address Book Program!!!");
        //initialize variable
        int option;
        //functionality
        while (true) {
            option = menu(); // display menu
            if (option == 1) {
                addContact(); // add contact
            } else if (option == 2) {
                editContact(); // edit contact
            } else if (option == 3) {
                displayContact(); // display contact
            } else if (option == 4) {
                System.out.println("Thank You!!! "); // user quits
                break;
            } else
                System.out.println("Invalid Input. Please enter a valid Input!!!! "); // invalid input
        }
    }
}