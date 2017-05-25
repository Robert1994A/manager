package ro.certificate.manager.controller;

import java.io.InputStream;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.certificate.manager.entity.Keystore;
import ro.certificate.manager.entity.User;
import ro.certificate.manager.exceptions.InternalServerError;
import ro.certificate.manager.wrapper.Certificate;
import ro.certificate.manager.wrapper.CertificateDetails;
import ro.certificate.manager.wrapper.ImportCertificate;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CertificateController extends BaseController {

	private static final Logger logger = Logger.getLogger(CertificateController.class);

	@RequestMapping(value = "/certificates", method = RequestMethod.GET)
	public String keystores(Model model, Principal principal,
			@RequestParam(value = "query", required = false) String query) {
		if (principal != null) {
			try {
				User user = userService.findByUsername(principal.getName());
				List<Keystore> keystores = null;
				if (query != null && query.trim().length() > 0) {
					keystores = keystoreService.findByUserAndCertificateSubjectContainingIgnoreCase(user, query);
				} else {
					keystores = keystoreService.findByUser(user);
				}

				if (keystores != null && !keystores.isEmpty()) {
					model.addAttribute("found", true);
					model.addAttribute("keystores", keystores);
				} else {
					model.addAttribute("found", false);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				model.addAttribute("error", "Cannot process your request: " + e.getMessage());
			}
		} else {
			return "redirect:/login";
		}

		return "/certificates";
	}

	@RequestMapping(value = "/generate_certificate", method = RequestMethod.GET)
	public String generate_certificate(Model model) {
		model.addAttribute("certificate", new Certificate());

		return "/generate_certificate";
	}

	@RequestMapping(value = "/generate_certificate", method = RequestMethod.POST)
	public String generate_certificatePOST(Model model, @Valid @ModelAttribute("certificate") Certificate certificate,
			BindingResult bindingResult, Principal principal) {
		if (principal != null) {
			if (bindingResult.hasErrors()) {
				return "/generate_certificate";
			}
			try {
				User user = userService.findByUsername(principal.getName());
				certificateGeneratorUtils.generateCertificate(certificate, user);
				model.addAttribute("success", true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				model.addAttribute("error", "Error in processing your request: " + e.getMessage());
			}
		} else {
			model.addAttribute("error", "Please authenticate to made this request!");
		}

		return "/generate_certificate";
	}

	@RequestMapping(value = "/delete_certificate", method = RequestMethod.POST)
	public String delete_certificatePOST(Model model, @RequestParam(value = "id", required = true) String id,
			Principal principal) {
		boolean deleted = false;
		if (principal != null) {
			try {
				User user = userService.findByUsername(principal.getName());
				certificateGeneratorUtils.deleteCertificate(user, id);
				deleted = true;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			model.addAttribute("error", "Please authenticate to made this request!");
		}
		model.addAttribute("deleted", deleted);

		return "redirect:" + keystores(model, principal, null);
	}

	@RequestMapping(value = "/import_certificate", method = RequestMethod.GET)
	public String import_certificate(Model model) {
		model.addAttribute("importCertificate", new ImportCertificate());

		return "/import_certificate";
	}

	@RequestMapping(value = "/import_certificate", method = RequestMethod.POST)
	public String import_certificatePOST(Model model,
			@Valid @ModelAttribute("importCertificate") ImportCertificate importCertificate,
			BindingResult bindingResult, Principal principal) {
		if (principal != null) {
			if (bindingResult.hasErrors()) {
				return "/import_certificate";
			}
			try {
				User user = userService.findByUsername(principal.getName());
				certificateGeneratorUtils.importCertificate(importCertificate.getCertificate(),
						importCertificate.getPrivateKey(), null, null, user);
				model.addAttribute("success", true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				model.addAttribute("error", "Error in processing your request: " + e.getMessage());
			}
		} else {
			model.addAttribute("error", "Please authenticate to made this request!");
		}

		return "/import_certificate";
	}

	@RequestMapping(value = "/upload_certificate", method = RequestMethod.GET)
	public String upload_certificate(Model model) {
		return "/upload_certificate";
	}

	@RequestMapping(value = "/upload_certificate", method = RequestMethod.POST)
	public String upload_certificatePOST(Principal principal, @RequestParam("certificate") MultipartFile certificate,
			@RequestParam("privateKey") MultipartFile privateKey, RedirectAttributes redirectAttributes) {
		if (principal != null) {
			try {
				User user = userService.findByUsername(principal.getName());
				certificateGeneratorUtils.uploadCertificate(certificate, privateKey, user);
				redirectAttributes.addFlashAttribute("success", true);
			} catch (Exception e) {
				logger.error(e.getMessage());
				redirectAttributes.addFlashAttribute("error", "Error in processing your request: " + e.getMessage());
			}
		} else {
			redirectAttributes.addFlashAttribute("error", "Please authenticate to made this request!");
		}

		return "redirect:/upload_certificate";
	}

	@RequestMapping(value = "/certificate_details/{id}", method = RequestMethod.GET)
	public String certificate_details(Model model, Principal principal, @PathVariable("id") String certificateID) {
		if (principal != null) {
			try {
				User user = userService.findByUsername(principal.getName());
				Keystore findedKeystore = findKeystore(user, certificateID);
				if (findedKeystore != null) {
					List<CertificateDetails> certificatesDetails = certificateGeneratorUtils
							.getCertificatesInfo(findedKeystore, user);
					if (!certificatesDetails.isEmpty()) {
						model.addAttribute("certificatesDetails", certificatesDetails);
					} else {
						model.addAttribute("error", "No certificate was found.");
					}
				} else {
					model.addAttribute("error", "No certificate was found.");
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				model.addAttribute("error", "Cannot process your request: " + e.getMessage());
			}
		} else {
			return "redirect:/login";
		}

		return "/certificate_details";
	}

	@RequestMapping(value = "/generate_csr/{id}", method = RequestMethod.GET)
	public void generate_csr(Principal principal, HttpServletResponse response,
			@PathVariable("id") String certificateID) {
		if (principal != null) {
			InputStream inputStream = null;
			try {
				User user = userService.findByUsername(principal.getName());
				Keystore findedKeystore = findKeystore(user, certificateID);
				if (findedKeystore != null) {
					inputStream = certificateGeneratorUtils.generateCSR(findedKeystore, user);
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + findedKeystore.getCertificateSubject() + ".csr\"");
					IOUtils.copy(inputStream, response.getOutputStream());
					response.flushBuffer();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new InternalServerError(e.getMessage());
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		}
	}

	@RequestMapping(value = "/export_privateKey/{id}", method = RequestMethod.GET)
	public void export_privateKey(Principal principal, HttpServletResponse response,
			@PathVariable("id") String certificateID) {
		if (principal != null) {
			InputStream inputStream = null;
			try {
				User user = userService.findByUsername(principal.getName());
				Keystore findedKeystore = findKeystore(user, certificateID);
				if (findedKeystore != null) {
					inputStream = certificateGeneratorUtils.export_privateKey(findedKeystore, user);
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + findedKeystore.getCertificateSubject() + ".key\"");
					IOUtils.copy(inputStream, response.getOutputStream());
					response.flushBuffer();
				}
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				throw new InternalServerError(e.getMessage());
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		}
	}

	@RequestMapping(value = "/export_certificate/{id}", method = RequestMethod.GET)
	public void export_certificate(Principal principal, HttpServletResponse response,
			@PathVariable("id") String certificateID) {
		if (principal != null) {
			InputStream inputStream = null;
			try {
				User user = userService.findByUsername(principal.getName());
				Keystore findedKeystore = findKeystore(user, certificateID);
				if (findedKeystore != null) {
					inputStream = certificateGeneratorUtils.export_certificate(findedKeystore, user);
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + findedKeystore.getCertificateSubject() + ".cer\"");
					IOUtils.copy(inputStream, response.getOutputStream());
					response.flushBuffer();
				}
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				throw new InternalServerError(e.getMessage());
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e2) {
					// Do nothing.
				}
			}
		}
	}

	/**
	 * Find a certificate based on user.
	 * 
	 * @param user
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Keystore findKeystore(User user, String id) throws Exception {
		List<Keystore> keyStores = keystoreService.findByUser(user);
		Keystore findedKeystore = null;
		if (keyStores != null && !keyStores.isEmpty()) {
			for (Keystore keystore : keyStores) {
				if (keystore.getId().equals(id)) {
					findedKeystore = keystore;
					break;
				}
			}
		}

		if (findedKeystore == null) {
			throw new Exception("No certificate was found.");
		}
		return findedKeystore;
	}
}
