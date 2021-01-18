package proj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
