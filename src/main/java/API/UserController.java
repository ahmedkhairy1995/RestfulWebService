package API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import API.UserData.User;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/user")
public class UserController {
    //private static ArrayList<User> usersList = new ArrayList<>();
    //private static final AtomicLong IDCount = new AtomicLong(0);

    @Autowired
    private UserService userService;

    //Create a new user
    @PostMapping("/")
    public ResponseEntity<Void> createUser(@RequestBody User user) {

        if (!userService.saveUser(user))
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /*
    //Get user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){

        for(User user: usersList){
            if(user.getID() == id){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Get all users
    @GetMapping("/")
    public ResponseEntity<ArrayList<User>> getAllUsers(){

        if(usersList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usersList, HttpStatus.FOUND);
    }



    @PutMapping("/")
    public  ResponseEntity <String> editUser(@RequestBody User editedUser) {
        if (usersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            for (User user : usersList) {
                if (user.getID() == editedUser.getID())
                    if (!user.getMobileNum().equals(editedUser.getMobileNum()))
                        return new ResponseEntity<>("Cannot edit mobile number", HttpStatus.FOUND);
                    else {
                        //user.setAddress(editedUser.getAddress());
                        user.setAge(editedUser.getAge());
                        user.setEmail(editedUser.getEmail());
                        user.setFullname(editedUser.getFullname());
                        user.setGender(editedUser.getGender());
                        user.setLocation(editedUser.getLocation());
                        user.setPassword(editedUser.getPassword());
                        return new ResponseEntity<>("Edited Successfully", HttpStatus.FOUND);
                    }
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
