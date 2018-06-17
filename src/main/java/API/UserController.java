package API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.UserData.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        if (!userService.saveUser(user))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Delete Existing User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
        if(userService.deleteUser(id))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Update user
    @PutMapping("/")
    public  ResponseEntity <String> editUser(@RequestBody User editedUser) {
        if(!userService.updateUser(editedUser))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    //Get user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        User user = userService.retrieveUser(id);
        if( user != null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get all users
    @GetMapping("/")
    public ResponseEntity<ArrayList<User>> getAllUsers(){

        if(userService.retrieveUsers().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userService.retrieveUsers(), HttpStatus.FOUND);
    }
    /*

    @GetMapping("/{id}/verify/")
    public ResponseEntity<String> verification(@PathVariable("id") long id) {
        for (User user : usersList){
            if(user.getID()==id) {
                user.setVerified(true);
                return new ResponseEntity<>("User is verified", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/suspend/")
    public ResponseEntity<String> suspension(@PathVariable("id") long id) {
        for (User user : usersList){
            if(user.getID()==id){
                user.setSuspended(true);
                return new ResponseEntity<>("User is suspended", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
    */
}
