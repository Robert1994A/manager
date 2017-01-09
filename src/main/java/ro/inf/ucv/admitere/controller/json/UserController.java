package ro.inf.ucv.admitere.controller.json;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.entity.utils.ApiError;
import ro.inf.ucv.admitere.exceptions.UserNotFound;

@RestController
@SessionAttributes("userSearchValue")
public class UserController extends BaseController {

	@RequestMapping(value = "/users/paginate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

	@RequestMapping(value = "/users/deleteAccount/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> deleteAccountJSON(@PathVariable("id") String id) {
		ApiError error;
		try {
			userService.deleteById(id);
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, "Error to delete this account!", new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "This account was deleted succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/disableAccount/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> disableAccountJSON(@PathVariable("id") String id) {
		ApiError error;
		try {
			user = userService.findOne(id);
			if (!user.isEnabled()) {
				error = new ApiError(HttpStatus.BAD_REQUEST, "This user is already disabled!", new ArrayList<>());
				return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
			}
			user.setEnabled(false);
			userService.save(user);
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "This account was disabled succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}

	@RequestMapping(value = "/users/enableAccount/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> enableAccountJSON(@PathVariable("id") String id) {
		ApiError error;
		try {
			user = userService.findOne(id);
			if (user.isEnabled()) {
				error = new ApiError(HttpStatus.BAD_REQUEST, "This user is already enabled!", new ArrayList<>());
				return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
			}
			user.setEnabled(true);
			userService.save(user);
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "This account was enabled succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}
}
