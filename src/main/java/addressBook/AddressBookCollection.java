/**
 * @uthor: Vinit Kumar
 * @created on: 21-Jan-2022
 * Address Books Collection
 */
package addressBook;


import java.util.*;

import static java.lang.System.exit;

public class AddressBookCollection {
    //initialize variables
    public static Map<String, AddressBook> addressBookMap = new HashMap<>();
    public static final Scanner sc = new Scanner(System.in);

    /**
     * menu to show Address Book
     * displays menu to add,view and operate
     * returns user selected option
     *
     * @return
     */
    public static int menu() {
        System.out.println(
                "Select an operation: \n1- To add new AddressBook\n2- To view AddressBooks\n3- To operate on " +
                        "AddressBooks \nOther to quit");
        return sc.nextInt();
    }

    /**
     * Add new address book
     * takes new address book name
     * created object
     * add to hash map
     */
    public static void addBook() {
        System.out.println("Enter name of new AddressBook");
        sc.nextLine();
        String name = sc.nextLine();
        if (addressBookMap.containsKey(name)) {
            System.out.println("Book with name " + name + " exists!!!");
            return;
        }
        AddressBook a = new AddressBook(name);
        addressBookMap.put(name, a);
        System.out.println("New Address Book added!!!");
    }

    /**
     * prints all Address book
     * iterate through collection
     * prints each book
     */
    public static void displayBooks() {
        System.out.println("Address Book: ");
        for (Map.Entry m : addressBookMap.entrySet()) {
            System.out.print(m.getKey() + " ");
        }
    }

    /**
     * performs operation on Book
     * take inputs name of book
     * checks if book is present
     * if present, call operations method of book
     */
    public static void operateBook() {
        System.out.println("Enter name of AddressBook to work: ");
        sc.nextLine();
        String bookName = sc.nextLine();
        if (addressBookMap.containsKey(bookName)) {
            addressBookMap.get(bookName).operations(addressBookMap.get(bookName));
        } else {
            System.out.println("Not Found");
        }
    }

    public static void main(String[] args) {
        //welcome message
        System.out.println("Welcome to Address Book Program!!!");
        //initialize variable
        int option;
        //functionality
        while (true) {
            option = menu();
            switch (option) {
                case (1):
                    addBook(); // add new book to collection
                    break;
                case (2):
                    displayBooks(); // display book in collection
                    break;
                case (3):
                    operateBook(); // operate on book
                    break;
                default:
                    System.out.println("Thanks for using Address Book!!!"); // quit book
                    exit(0);
                    break;
            }
        }
    }
}
