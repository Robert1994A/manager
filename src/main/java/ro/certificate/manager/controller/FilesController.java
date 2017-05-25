package ro.certificate.manager.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FilesController extends BaseController {

	private static final Logger logger = Logger.getLogger(FilesController.class);

	@RequestMapping(value = "/admin/files", method = RequestMethod.GET)
	public String filesPage(Model model) {
		//List<File> files = fileService.findAll(new File(servletContext.getRealPath("/") + "/resources/files").getAbsolutePath());
		List<File> files = new ArrayList<>();
		if (files != null && !files.isEmpty()) {
			model.addAttribute("exist", true);
			model.addAttribute("files", files);
		} else {
			model.addAttribute("exist", false);
		}
		return "/admin/files";
	}

	@RequestMapping(value = "/admin/files/add_file", method = RequestMethod.GET)
	public String addFilePage() {
		return "/admin/add_file";
	}

	@RequestMapping(value = "/admin/files/add_file", method = RequestMethod.POST)
	public String uploadMultipleFileHandler(Model model, @RequestParam("file") MultipartFile[] files,
			@RequestParam("name") String[] fileNames, Principal principal, HttpSession httpSession,
			RedirectAttributes redirectAttributes) throws Exception {
		boolean found = false;
		for (int i = 0; i < files.length; i++) {
			BufferedOutputStream stream = null;
			try {
				MultipartFile file = files[i];
				String fileName = fileNames[i];
				if (file.isEmpty()) {
					redirectAttributes.addFlashAttribute("emptyFile", true);
					return "redirect:/admin/files/add_file";
				} else {
					String originalFileName = file.getOriginalFilename();
					String extension = FilenameUtils.getExtension(originalFileName);

					if (!extensionsUtils.isExtensionFileValid(extension)) {
						redirectAttributes.addFlashAttribute("wrongFileExtension", true);
						List<String> supportedExtensionsFiles = extensionsUtils.getSupportedExtensionsFiles();
						if (supportedExtensionsFiles != null && !supportedExtensionsFiles.isEmpty()) {
							redirectAttributes.addFlashAttribute("supportedFileExtension",
									supportedExtensionsFiles.toString());
						}
						return "redirect:/admin/files/add_file";
					}
					File filesDirectory = new File(servletContext.getRealPath("/") + "/resources/files");
					if (!filesDirectory.exists()) {
						filesDirectory.mkdirs();
					}
					int year = Calendar.getInstance().get(Calendar.YEAR);
					int month = Calendar.getInstance().get(Calendar.MONTH);
					int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					// Create the file on server.
					if (fileName != null && fileName.trim().length() > 0) {
						fileName = fileName + "_" + year + "_" + month + "_" + day + "_"
								+ stringGenerator.getRandomNumberForFileName();
					} else {
						fileName = originalFileName + year + month + day + stringGenerator.getRandomNumberForFileName();
					}
					File serverFile = new File(
							filesDirectory.getAbsolutePath() + File.separator + fileName + "." + extension);
					if (!serverFile.exists()) {
						serverFile.createNewFile();
					} else {
						serverFile = new File(filesDirectory.getAbsolutePath() + File.separator + fileName + "_"
								+ stringGenerator.getRandomNumberForFileName() + "." + extension);
					}

					stream = new BufferedOutputStream(new FileOutputStream(serverFile));

					byte[] bytes = file.getBytes();
					stream.write(bytes);
					found = true;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				redirectAttributes.addFlashAttribute("success", false);
				return "redirect:/admin/files/add_file";
			} finally {
				try {
					if (stream != null) {
						stream.close();
					}
				} catch (Exception e2) {
					// Silent.
				}
			}
		}
		redirectAttributes.addFlashAttribute("success", found);
		return "redirect:/admin/files/add_file";
	}
}
