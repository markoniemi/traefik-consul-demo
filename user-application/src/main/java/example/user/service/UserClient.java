package example.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import example.repository.user.User;
import example.repository.user.UserService;

@Service
@FeignClient(name = "user-repository-v1", path = "/api/rest/users/v1")
public interface UserClient extends UserService {

    @Override
    @GetMapping
    User[] findAll();

    @Override
    @PostMapping
    User create(@RequestBody User user);

    @Override
    @PutMapping
    User update(@RequestBody User user);

    @Override
    @GetMapping(value = "/{id}")
    User findById(@PathVariable("id") Long id);

    @Override
    @GetMapping(params = "username")
    public User findByUsername(@RequestParam("username") String username);

    @Override
    @GetMapping(params = "email")
    public User findByEmail(@RequestParam("email") String email);

    @Override
    @GetMapping(value = "/exists/{id}")
    boolean exists(@PathVariable("id") Long id);

    @Override
    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable("id") Long id);

    @Override
    @GetMapping(value = "/count")
    long count();
}
