package ro.inf.ucv.admitere.controller.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ro.inf.ucv.admitere.entity.Role;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.service.RoleService;
import ro.inf.ucv.admitere.service.UserService;
import ro.inf.ucv.admitere.utils.Generator;
import ro.inf.ucvadmitere.list.UserList;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableWebMvc
public class HomeControllerRest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    public void initDatabase() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Generator myGenerator = new Generator();

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleService.save(roleAdmin);

        Role roleModerator = new Role();
        roleAdmin.setName("ROLE_MODERATOR");
        roleService.save(roleModerator);

        List<Role> rolesModerator = new ArrayList<Role>();
        rolesModerator.add(roleAdmin);
        rolesModerator.add(roleUser);

        List<Role> rolesUser = new ArrayList<Role>();
        rolesUser.add(roleUser);

        List<Role> roleSuperAdmin = new ArrayList<Role>();
        roleSuperAdmin.add(roleModerator);
        roleSuperAdmin.add(roleAdmin);
        roleSuperAdmin.add(roleUser);

        for (int i = 0; i < 50; i++) {
            User userAdmin = new User();
            userAdmin.setEnabled(true);
            userAdmin.setEmail("admin@gmail.com" + i);
            userAdmin.setToken("tokenAdmin" + i);
            userAdmin.setUsername("admin" + i);
            userAdmin.setPassword(encoder.encode("admin" + i));
            userAdmin.setRoles(roleSuperAdmin);
            userService.save(userAdmin);
        }

        for (int i = 0; i < 50; i++) {
            User user1 = new User();
            user1.setEnabled(true);
            user1.setEmail("user@gmail.com" + i);
            user1.setToken("tokenUser" + i);
            user1.setUsername("user" + i);
            user1.setPassword(encoder.encode("user" + i));
            user1.setRoles(rolesUser);
            userService.save(user1);
        }

        for (int i = 0; i < 50; i++) {
            User moderator = new User();
            moderator.setEnabled(true);
            moderator.setEmail("moderator@gmail.com" + i);
            moderator.setToken("tokenModerator" + i);
            moderator.setUsername(myGenerator.getGeneratedString());
            moderator.setPassword(encoder.encode("moderator" + i));
            moderator.setRoles(rolesModerator);
            userService.save(moderator);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        //initDatabase();
        return "blank";
    }

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
