package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.json.objects.UsersCountObject;

@RestController
public class UserController extends BaseController {

	@RequestMapping(value = "/json/users/{id}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User findOneJSON(@PathVariable("id") Long id) throws UserNotFound {
		User user = userService.findOne(id);
		if (user == null) {
			return null;
		}
		return user;
	}

	@RequestMapping(value = "/json/users/countUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UsersCountObject countUsersJSON() {
		UsersCountObject usersCountObject = new UsersCountObject();
		usersCountObject.setCountUsers(userService.count());
		return usersCountObject;
	}

	@RequestMapping(value = "/json/users.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> allUsersJSON() throws UserNotFound {
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

	@RequestMapping(value = "/json/users/paginate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<User> allUsersPaginateJSON(
			@RequestParam(value = "pageNumber", required = true) int pageNumber,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "sortBy", required = true) String sortBy) throws UserNotFound {
		List<User> users = this.userService.findAll(new PageRequest(pageNumber, pageSize, Direction.ASC, sortBy))
				.getContent();
		if (users == null) {
			return null;
		}
		for (User user : users) {
			user.getRoles();
		}
		return users;
	}
}
