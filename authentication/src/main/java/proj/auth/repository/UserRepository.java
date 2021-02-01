package proj.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
