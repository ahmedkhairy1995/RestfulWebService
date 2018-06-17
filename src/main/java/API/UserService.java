package API;

import API.UserData.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.Collections;
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
