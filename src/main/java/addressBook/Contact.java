/**
 * @uthor: Vinit Kumar
 * @created on: 31-Dec-2021
 * Address Book
 */
package addressBook;

//Contact
class Contact {
    //instance variable declaration
    private String firstName, lastName, address, city, state, email;
    private int zip;
    long p_number;

    //constructor
    Contact(String firstName, String lastName, String address, String city, String state, int zip, long p_number,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.p_number = p_number;
        this.email = email;

    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        return (" Name: " + this.getFirstName() + " " + this.getLastName() + "\n Address: " + this.getAddress() + " ," +
                this.getCity() + " ," + this.getState() + " ," + this.getZip() + "\n Contact: " + this.getP_number() +
                "\n Email: " + this.getEmail()+"\n");
    }

}
