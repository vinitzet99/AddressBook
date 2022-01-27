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
    public static Map<String, List<Contact>> cityDictionary = new HashMap<>();
    public static Map<String, List<Contact>> stateDictionary = new HashMap<>();

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
                        "AddressBooks\n4-Filter by one City/State \n5- To map by City \n6- To map by State \n7- To " +
                        "sort \nOther to quit");
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

    /**
     * search persons by city or state
     * takes input search on City or State
     * take name of City/State
     * iterative calls respective searchPersonBy** method for each book
     */
    public static void searchPerson() {
        System.out.println("Select an operation: \n1. City \n2.State");
        int option = sc.nextInt();
        switch (option) {
            case (1):
                System.out.println("Enter City to search from: ");
                sc.nextLine();
                String city = sc.nextLine();
                for (AddressBook addressBook : addressBookMap.values()) {
                    addressBook.searchPersonByCity(city);
                }
                break;
            case (2):
                System.out.println("Enter State to search from: ");
                sc.nextLine();
                String state = sc.nextLine();
                for (AddressBook addressBook : addressBookMap.values()) {
                    addressBook.searchPersonByState(state);
                }
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    /**
     * Map City with Person
     * calls map method for each book
     * creates map
     * assign to cityDictionary
     * prints cityDictionary
     */
    public static void cityDictionaryMap() {
        Map<String, List<Contact>> dictMap = new HashMap<>();
        for (AddressBook book : addressBookMap.values()) {
            book.cityMap().forEach((key, value) -> dictMap.merge(key, value,
                    (city, contact) -> {
                        city.addAll(contact);
                        return city;
                    }));
        }
        cityDictionary = dictMap;
        cityDictionary.forEach(((key, value) -> System.out.println(key + " " + value+"Total Count: "+value.size())));
    }

    /**
     * Map State with Person
     * calls map method for each book
     * creates map
     * assign to stateDictionary
     * prints stateDictionary
     */
    public static void stateDictionaryMap() {
        Map<String, List<Contact>> dictMap = new HashMap<>();
        for (AddressBook book : addressBookMap.values()) {
            book.stateMap().forEach((key, value) -> dictMap.merge(key, value,
                    (state, contact) -> {
                        state.addAll(contact);
                        return state;
                    }));
        }
        stateDictionary = dictMap;
        stateDictionary.forEach(((key, value) -> System.out.println(key + " " + value+" Total count is: "+value.size())));
    }

    /**
     * Sorts Contacts
     * input parameter to sort upon Name,City,State,Zip
     * iterate for each book
     * call sort method with parameter
     */
    public static void sort() {
        System.out.println("Select option to sort");
        System.out.println("Select an operation: \n1- by Name\n2- by City\n3- by State \n4- by Zip");
        int option = sc.nextInt();
        switch (option) {
            case (1):
                for (AddressBook book : addressBookMap.values()) {
                    book.sort("name");
                }
                break;
            case (2):
                for (AddressBook book : addressBookMap.values()) {
                    book.sort("city");
                }
                break;
            case (3):
                for (AddressBook book : addressBookMap.values()) {
                    book.sort("state");
                }
                break;
            case (4):
                for (AddressBook book : addressBookMap.values()) {
                    book.sort("zip");
                }
                break;
            default:
                System.out.println("Invalid Input");
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
                case (4):
                    searchPerson(); // search Person
                    break;
                case (5):
                    cityDictionaryMap(); // person by City
                    break;
                case (6):
                    stateDictionaryMap(); // person by State
                    break;
                case (7):
                    sort();// sort by person name
                    break;
                default:
                    System.out.println("Thanks for using Address Book!!!"); // quit book
                    exit(0);
                    break;
            }
        }
    }
}
