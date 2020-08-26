package example.user.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import example.user.service.UserClient;

@Controller
public class UsersController {
    @Resource
    private UserClient userService;

    @GetMapping(value = "/user/users")
    public ModelAndView users() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/users");
        model.addObject("users", userService.findAll());
        return model;
    }
}
