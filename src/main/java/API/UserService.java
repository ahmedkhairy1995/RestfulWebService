package API;

import API.UserData.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //Create new User
    public boolean saveUser(User user) {
        if(isFound(user.getEmail()))
            return false;
        else{
            userRepository.save(user);
            return true;
        }
    }
    //Update the user info except ID and mob num
    public boolean updateUser(User updatedUser){
        Optional<User> userFound = userRepository.findByMobileNum(updatedUser.getMobileNum());
        if(userFound.isPresent()){
            User userToUpdate = userFound.get();
            userToUpdate.setLocations(updatedUser.getLocations());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setAge(updatedUser.getAge());
            userToUpdate.setFullname(updatedUser.getFullname());
            userToUpdate.setGender(updatedUser.getGender());
            userToUpdate.setPassword(updatedUser.getPassword());
            userRepository.save(userToUpdate);
            return true;
        }
        else
            return false;
    }

    //Retrieve user by ID
    public User retrieveUser(long ID){
        Optional<User> userFound = userRepository.findById(ID);
        if(userFound.isPresent())
            return userFound.get();

        else
            return null;
    }
    //Retrieve all users
    public ArrayList<User> retrieveUsers(){
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users:: add);
        return users;
    }

    //Delete Existing User
    public boolean deleteUser(long id) {
        if(existing(id)){
            userRepository.setSuspendedFor(true,id);
            return true;
        }
        return false;
    }

    //Check if it is a new user
    public boolean isFound(String email) {
        Optional<User> userFound = userRepository.findByEmail(email);
        return userFound.isPresent();
    }

    //Check if it is an existing user
    public boolean existing(long id) {
        return userRepository.existsById(id);
    }
}
