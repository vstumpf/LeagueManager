package com.vstumpf.LoLManager.controller;

import com.vstumpf.LoLManager.data.LMUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent on 7/23/2017.
 */

@RestController
public class LMUserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public LMUser user(@RequestParam(value="name", defaultValue="World") String name) {
        return new LMUser(name, "fakeemail");
    }
}
