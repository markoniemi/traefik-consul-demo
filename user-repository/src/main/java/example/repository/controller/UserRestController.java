package example.repository.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.repository.user.User;
import example.repository.user.UserRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/rest")
@Log4j2
public class UserRestController {
    @Resource
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public User[] findAll() {
        log.trace("findAll");
        return userRepository.findAll().toArray(new User[0]);
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) {
        log.trace("create: {}", user);
        return userRepository.save(user);
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) {
        User databaseUser = userRepository.findByUsername(user.getUsername());
        Validate.notNull(databaseUser, "User does not exist.");
        databaseUser.setEmail(user.getEmail());
        databaseUser.setPassword(user.getPassword());
        databaseUser.setRole(user.getRole());
        databaseUser.setUsername(user.getUsername());
        log.trace("update: {}", databaseUser);
        return userRepository.save(databaseUser);
    }

    @DeleteMapping(value = "/users/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @GetMapping(value = "/users/{id}")
    public User findById(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/users", params = "username")
    public User findByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping(value = "/users", params = "email")
    public User findByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping(value = "/users/exists/{id}")
    public boolean exists(@PathVariable("id") Long id) {
        return userRepository.existsById(id);
    }

    @GetMapping(value = "/users/count")
    public long count() {
        return userRepository.count();
    }
}
