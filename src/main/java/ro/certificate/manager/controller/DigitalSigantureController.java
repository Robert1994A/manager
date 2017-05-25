package ro.certificate.manager.controller;

import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ro.certificate.manager.wrapper.CertificateDetails;

@Controller
public class DigitalSigantureController extends BaseController {

	@RequestMapping(value = "/sign_document", method = RequestMethod.GET)
	public String sign_document(Model model) {
		return "/sign_document";
	}

	@RequestMapping(value = "/retrieve_certificates", method = RequestMethod.GET)
	public String retrieve_certificates(Model model) {
		return "/retrieve_certificates";
	}

	@RequestMapping(value = "/sign_document", method = RequestMethod.POST)
	public void sign_documentPOST(Principal principal, @RequestParam("documentToSign") MultipartFile documentToSign,
			@RequestParam("privateKey") MultipartFile privateKey, HttpServletResponse response) throws Exception {
		if (principal != null) {
			InputStream inputStream = null;
			try {
				inputStream = certificateGeneratorUtils.signDocument(documentToSign, privateKey);
				response.setHeader("Content-Disposition", "attachment; filename=\"signed_document.pdf\"");
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
			} catch (Exception e) {
				throw new InternalError(e.getMessage());
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

	@RequestMapping(value = "/retrieve_certificates", method = RequestMethod.POST)
	public String retrieve_certificatesPOST(Model model, @RequestParam(value = "URL", required = false) String url,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "host", required = false) String host,
			@RequestParam(value = "port", required = false) Integer port) {

		URL resultedURL = null;
		List<CertificateDetails> certificateDetails = null;
		Exception ex = null;
		try {
			if (type != null) {
				if (type.equals("byURL") && url != null && url.trim().length() > 0) {
					if (!url.startsWith("https")) {
						resultedURL = new URL("https", url, "");
					} else {
						resultedURL = new URL(url);
					}
				} else if (type.equals("byHost") && host != null && host.trim().length() > 0 && port > 0) {
					resultedURL = new URL("https", host, port, "");
				}
			}
			if (resultedURL != null) {
				certificateDetails = certificateGeneratorUtils.retrieveCertificates(resultedURL);
			}
		} catch (Exception e) {
			model.addAttribute("error", "Cannot process your request: " + e.getMessage());
			ex = e;
		}
		if (certificateDetails != null && !certificateDetails.isEmpty()) {
			model.addAttribute("certificatesDetails", certificateDetails);
		} else if (ex == null) {
			model.addAttribute("error", "No certificate was found.");
		}

		return "/parts/certificate_detail";
	}
}
