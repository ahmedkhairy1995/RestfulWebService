package API;

import API.UserData.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNum(String mobileNum);

    @Modifying
    @Query("UPDATE User u SET u.suspended = :suspended WHERE u.id = :id")
    void setSuspendedFor(@Param("suspended") boolean suspended, @Param("id") long id);
}