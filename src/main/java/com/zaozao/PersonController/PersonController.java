package com.zaozao.PersonController;

import com.zaozao.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Sidney 2019-01-08.
 */
@Controller("personController")
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/getPerson", method = {RequestMethod.GET})
    public String getPerson(@RequestParam("guid") String guid) throws Exception {
        String result = personService.getPerson(guid);
        return "lvlv.html";
    }
}
