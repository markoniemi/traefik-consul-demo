package example;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import example.repository.user.Role;
import example.repository.user.User;
import example.user.service.UserClient;

public class UserRestControllerIT extends AbstractIntegrationTestBase {
    @Resource
    UserClient userClient;

    @Before
    public void setUp() throws InterruptedException {
        waitForServiceRegistration();
    }

    @Test
    public void users() {
        User[] users = userClient.findAll();
        Assert.assertNotNull(users);
        Assert.assertEquals("admin", users[0].getUsername());
    }

    @Test
    public void userByUsername() {
        User user = userClient.findByUsername("admin");
        Assert.assertNotNull(user);
        Assert.assertEquals("admin", user.getUsername());
    }

    @Test
    public void findById() {
        User user = userClient.findById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("admin", user.getUsername());
    }

    @Test
    public void create() {
        User user = userClient.create(new User("user", "user", "email", Role.ROLE_USER));
        Assert.assertNotNull(user);
        Assert.assertEquals("user", user.getUsername());
        user = userClient.findById(user.getId());
        Assert.assertNotNull(user);
        userClient.delete(user.getId());
        user = userClient.findById(user.getId());
        Assert.assertNull(user);
    }

    @Test
    public void count() {
        Assert.assertEquals(1L, userClient.count());
    }
}
