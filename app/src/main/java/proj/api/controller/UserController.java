package proj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import proj.api.model.User;
import proj.api.service.UserService;
import proj.api.util.URIParamParser;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        service.save(user);
    }

    @GetMapping("/sort")
    public List<User> getUsersOrderedBy(@RequestParam String param) {
        Map<String, Map<String, String>> paramsMap = URIParamParser.parse(param);
        return service.getUsersSortedBy(paramsMap);
    }
}
