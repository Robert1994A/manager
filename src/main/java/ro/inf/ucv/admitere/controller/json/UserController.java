package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.exceptions.UserNotFound;
import ro.inf.ucv.admitere.json.objects.UsersCountObject;

@RestController
@SessionAttributes("userSearchValue")
public class UserController extends BaseController {
	private static final String JSON_USERS_PATH = "/json/users/";

	@RequestMapping(value = JSON_USERS_PATH
			+ "{id}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User findOneJSON(@PathVariable("id") Long id) throws UserNotFound {
		User user = userService.findOne(id);
		if (user == null) {
			return null;
		}
		return user;
	}

	@RequestMapping(value = JSON_USERS_PATH
			+ "countUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

	@RequestMapping(value = JSON_USERS_PATH
			+ "paginate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<User>> allUsersPaginateJSON(
			@RequestParam(value = "pageNumber", required = true) int pageNumber,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "searchUser") String userSearch,
			@RequestParam(value = "direction") String sortDirection, Model model, HttpSession session)
			throws UserNotFound {

		Page<User> users = null;
		Direction direction = Direction.ASC;

		if (sortDirection.equals("DESC")) {
			direction = Direction.DESC;
		}

		if (userSearch != null && userSearch.trim().length() > 0) {
			session.setAttribute("userSearchValue", userSearch);
		}
		String userSearchValue = (String) session.getAttribute("userSearchValue");
		PageRequest pageRequest = new PageRequest(pageNumber, pageSize, direction, sortBy);
		if (userSearchValue != null && userSearchValue.trim().length() > 0) {
			users = this.userService.searchUser(userSearchValue, pageRequest);
		} else {
			users = this.userService.findAll(pageRequest);
		}

		if (users == null) {
			return new ResponseEntity<Page<User>>(HttpStatus.NO_CONTENT);
		}

		for (User user : users) {
			user.getRoles();
		}

		return new ResponseEntity<Page<User>>(users, HttpStatus.OK);
	}
}
