package example.user.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import example.user.service.UserClient;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class UsersController {
    @Resource
    private UserClient userService;

    @GetMapping(value = "/user/users")
    public ModelAndView users() {
    	log.info("get /user/users");
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/users");
        model.addObject("users", userService.findAll());
        return model;
    }
}
