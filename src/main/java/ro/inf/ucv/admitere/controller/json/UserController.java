package ro.inf.ucv.admitere.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/json/users/{id}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    User findOneJSON(@PathVariable("id") Long id) throws UserNotFound {
        User user = userService.findOne(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @RequestMapping(value = "/json/users.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    List<User> allUsersJSON() throws UserNotFound {
        List<User> users = new ArrayList<User>();
        users = userService.findAll();
        if (users == null) {
            return null;
        }
        for (User user : users) {
            user.getRoles();
        }
        return users;
    }
}
