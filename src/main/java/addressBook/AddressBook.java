/**
 * @uthor: Vinit Kumar
 * @created on: 31-Dec-2021
 * Address Book
 */
package addressBook;
import java.util.Scanner;
//
class Contact {
    private String f_name, l_name, address, city, state, email;
    private int zip;
    long p_number;

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

}


public class AddressBook {
    //main method
    public static void main(String[] args) {
        //welcome message
        System.out.println("Welcome to Address Book Program!!!");
        //initialize variable
        Scanner sc=new Scanner(System.in);
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
        //creating contact
        Contact contact = new Contact(f_name, l_name, address, city, state, zip, p_number, email);
        //output
        System.out.println("Contact Created!!!");
    }
}