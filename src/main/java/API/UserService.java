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
        if(!isNew(user))
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

    //Check if it is a new user
    private boolean isNew(User newUser) {
        Optional<User> userFound = userRepository.findByEmail(newUser.getEmail());
        if(userFound.isPresent()){
            return false;
        }
        return true;
    }

    public void find(User user){
        Optional<User> u = userRepository.findById(user.getID());
        System.out.print(u);
    }

}
