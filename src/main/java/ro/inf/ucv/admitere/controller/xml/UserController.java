package ro.inf.ucv.admitere.controller.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.list.UserList;
import ro.inf.ucv.admitere.service.UserService;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/xml/users/{id}.xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public
    @ResponseBody
    User findOneXML(@PathVariable("id") Long id) throws UserNotFound {
        User user = userService.findOne(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @RequestMapping(value = "/xml/users.xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public
    @ResponseBody
    UserList allUsersXML() throws UserNotFound, JAXBException {
        UserList users = new UserList(userService.findAll());
        List<User> users1 = users.getUserList();
        for (User user : users1) {
            user.getRoles();
        }
        users.setUsers(users1);
        return users;
    }

}
