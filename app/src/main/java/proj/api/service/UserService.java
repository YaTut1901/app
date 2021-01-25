package proj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.api.model.User;
import proj.api.repository.UserRepository;
import proj.api.util.Sequencer;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private UserRepository repository;
    private Sequencer sequencer;

    @Autowired
    public UserService(UserRepository repository, Sequencer sequencer) {
        this.repository = repository;
        this.sequencer = sequencer;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public User get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<User> getUsersSortedBy(Map<String, Map<String, String>> param) {
        return repository.findAll(sequencer.orderBy(param));
    }
}
