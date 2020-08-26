package example;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import example.repository.user.Role;
import example.repository.user.User;
import example.selenium.LoginPage;
import example.selenium.UserPage;
import example.selenium.UsersPage;
import example.user.service.UserClient;

public class UserApplicationIT extends AbstractIntegrationTestBase {
    private String loginUrl = "http://localhost:8083/";
    @Resource
    private UserClient userService;
    @Resource
    protected WebDriver webDriver;
    private LoginPage loginPage;
    private UsersPage usersPage;
    private UserPage userPage;

    @Before
    public void setUp() {
        loginPage = new LoginPage(webDriver);
        usersPage = new UsersPage(webDriver);
        userPage = new UserPage(webDriver);
    }

    @After
    public void tearDown() {
        deleteUserFromRepository("admin_user");
        deleteUserFromRepository("user_user");
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void integrationTest() throws InterruptedException {
        webDriver.get(loginUrl);
        loginPage.login("admin", "admin");
        usersPage.clickAddUser();
        userPage.addUser("admin_user", "admin_user@test.com", "another", Role.ROLE_ADMIN);
        usersPage.assertUserRole("admin_user", "Admin");
        usersPage.clickAddUser();
        userPage.addUser("user_user", "user_user@test.com", "another", Role.ROLE_USER);
        usersPage.assertUserRole("user_user", "User");
        usersPage.deleteUser("user_user");
        usersPage.logout();
        loginPage.login("admin_user", "another");
        usersPage.editUser("admin_user");
        userPage.editUser("admin_user", "newpassword");
        usersPage.logout();
        loginPage.login("admin_user", "newpassword");
        usersPage.deleteUser("admin_user");
        usersPage.logout();
    }

    private void deleteUserFromRepository(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            userService.delete(user.getId());
        }
    }
}
