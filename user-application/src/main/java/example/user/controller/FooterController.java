package example.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class FooterController {
    @Value("runtime.environment")
    private String environment;

    @ModelAttribute
    public void setEnvironment(Model model) {
        model.addAttribute("environment", environment);
    }
}
