package milos.sf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({" ", "/", "/index"} )
    public String getIndexPage() {
        System.out.println("Some message to say .... 123242sssAAA4444AAAAA  55555 ssss ");
        return "index";
    }
}
