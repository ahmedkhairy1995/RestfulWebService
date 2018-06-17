package API.UserData;

import API.LocationData.Location;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Location> locations = new ArrayList<>();

    private String fullname;
    private String mobileNum;
    private String password;
    private String email;
    private boolean suspended = false;
    private boolean verified = false;
    private int gender;
    private int age;
    //private String address;


    public User(){}

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  // public String getAddress() { return address; }

  // public void setAddress(String address) { this.address = address; }

    public List<Location> getLocations() { return this.locations; }

    public void setLocations(List<Location> locations) { this.locations = locations; }

    public int getGender() { return gender; }

    public void setGender(int gender) { this.gender = gender; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public long getID() { return id; }

    public void setID(long id) { this.id = id; }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) { this.suspended = suspended; }

    public boolean isVerified() { return verified; }

    public void setVerified(boolean verified) { this.verified = verified; }

    @Override
    public String toString() { return fullname + " - " + email; }
}
