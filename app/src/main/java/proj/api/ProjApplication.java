package proj.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proj.api.model.User;
import proj.api.service.UserService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ProjApplication {
    private UserService service;

    @Autowired
    public ProjApplication(UserService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        User user_1 = new User("login_1", "1234", "name_2");
        User user_2 = new User("login_2", "1234", "name_4");
        User user_3 = new User("login_2", "1234", "name_3");
        User user_4 = new User("login_2", "1234", "name_6");
        User user_5 = new User("login_5", "1234", "name_5");
        service.save(user_1);
        service.save(user_2);
        service.save(user_3);
        service.save(user_4);
        service.save(user_5);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjApplication.class, args);
    }

}
