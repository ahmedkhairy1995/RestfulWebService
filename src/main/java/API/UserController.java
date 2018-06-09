package API;
import API.LocationData.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.UserData.User;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {

    private static ArrayList<User> usersList = new ArrayList();
    private static final AtomicLong IDCount = new AtomicLong(0);

    //Create a new user
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        if (!isNew(user))
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        else {

            user.setID(this.IDCount.incrementAndGet());
            this.usersList.add(user);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    //Get user
    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("ID") long ID){

        for(User user: this.usersList){
            if(user.getID() == ID){
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    //Get all users
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<User>> getAllUsers(){

        if(this.usersList.isEmpty()){
            return new ResponseEntity<ArrayList<User>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ArrayList<User>>(this.usersList, HttpStatus.FOUND);
    }

    //Check if it is a new user
    private boolean isNew(User newUser) {
        for(User user : this.usersList){
            if(user.getFullname().equalsIgnoreCase(newUser.getFullname()))
                return false;
        }
        return true;
    }


    @PutMapping("")
    public void editUser(){

    }

}
