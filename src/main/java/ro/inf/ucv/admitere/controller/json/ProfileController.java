package ro.inf.ucv.admitere.controller.json;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ro.inf.ucv.admitere.entity.LegalParentFather;
import ro.inf.ucv.admitere.entity.LegalParentMother;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.entity.UserPersonalData;
import ro.inf.ucv.admitere.entity.utils.ApiError;

@RestController
public class ProfileController extends BaseController {

	@RequestMapping(value = "/profile/personalFilesUpload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files,
			Principal principal) {

		ApiError apiError;
		ArrayList<ro.inf.ucv.admitere.entity.File> filesList = new ArrayList<>();
		try {
			User authenticatedUser = userService.findByUsername(principal.getName());

			UserPersonalData userPersonalData = authenticatedUser.getUserPersonalData();
			if (userPersonalData == null) {
				apiError = new ApiError(HttpStatus.BAD_REQUEST, "You must complete personal data before to add files!",
						new ArrayList<>());
				return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
			}

			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				String name = UUID.randomUUID().toString();
				String originalFileName = file.getOriginalFilename();
				String extension = FilenameUtils.getExtension(originalFileName);

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File directory = new File(rootPath + File.separator + "tmpFiles");
				if (!directory.exists()) {
					directory.mkdirs();
				}
				// Create the file on server
				File serverFile = new File(directory.getAbsolutePath() + File.separator + name + "." + extension);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				byte[] bytes = file.getBytes();
				stream.write(bytes);
				if (stream != null) {
					stream.close();
				}

				// Save to database
				ro.inf.ucv.admitere.entity.File a = new ro.inf.ucv.admitere.entity.File();
				a.setCreatedDate(new Date());
				a.setName(originalFileName);
				a.setUrl(name);
				a = fileService.save(a);
				filesList.add(a);
			}
			filesList.addAll(userPersonalData.getFiles());
			userPersonalData.setFiles(filesList);
			userPersonalDataService.save(userPersonalData);
		} catch (Exception e1) {
			apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error in processing your request!", new ArrayList<>());
			return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		}

		if (filesList.size() > 0) {
			apiError = new ApiError(HttpStatus.OK, "Your files was uplloaded succesfully!", new ArrayList<>());
		} else {
			apiError = new ApiError(HttpStatus.BAD_REQUEST, "Error in processing your request!", new ArrayList<>());
		}
		return new ResponseEntity<ApiError>(apiError, HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/savePersonalData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> saveUserPersonalDataJSON(@RequestBody @Valid UserPersonalData userPersonalData,
			Principal principal) {
		ApiError error;
		try {
			if (userPersonalData != null) {
				User authentiatedUser = userService.findByUsername(principal.getName());
				UserPersonalData user = userPersonalDataService.save(userPersonalData);
				authentiatedUser.setUserPersonalData(user);
				userService.save(authentiatedUser);
			}
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "This account was saved succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/saveLegalParentFather", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> saveLegalParentFatherJSON(@RequestBody @Valid LegalParentFather legalParentFather,
			Principal principal) {
		ApiError error;
		try {
			if (legalParentFather != null) {
				legalParentFather = legalParentFatherService.save(legalParentFather);
				User authentiatedUser = userService.findByUsername(principal.getName());
				UserPersonalData userPersonalData = authentiatedUser.getUserPersonalData();
				userPersonalData.setLegalParentFather(legalParentFather);
				userPersonalDataService.save(userPersonalData);
			}
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "Data was saved succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/saveLegalParentMother", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiError> saveLegalParentMotherJSON(@RequestBody @Valid LegalParentMother legalParentMother,
			Principal principal) {
		ApiError error;
		try {
			if (legalParentMother != null) {
				legalParentMother = legalParentMotherService.save(legalParentMother);
				User authentiatedUser = userService.findByUsername(principal.getName());
				UserPersonalData userPersonalData = authentiatedUser.getUserPersonalData();
				userPersonalData.setLegalParentMother(legalParentMother);
				userPersonalDataService.save(userPersonalData);
			}
		} catch (Exception e) {
			error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), new ArrayList<>());
			return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);
		}
		error = new ApiError(HttpStatus.OK, "Data was saved succesfully!", new ArrayList<>());
		return new ResponseEntity<ApiError>(error, HttpStatus.OK);
	}
}
