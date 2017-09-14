package admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(name="/index", method= RequestMethod.GET)
    public ModelAndView toIndexPage(){
        return new ModelAndView("index");
    }

}
