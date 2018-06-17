package API;

import API.UserData.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNum(String mobileNum);
    Optional<User> findById(long ID);
}