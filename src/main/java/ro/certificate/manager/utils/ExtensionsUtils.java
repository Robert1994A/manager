package ro.certificate.manager.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExtensionsUtils {

	@Value("#{'${gallery.supportedExtensions}'.split(';')}")
	protected List<String> supportedExtensionsGallery;

	@Value("#{'${file.supportedExtensions}'.split(';')}")
	protected List<String> supportedExtensionsFiles;

	public boolean isExtensionFileValid(String extension) {
		boolean validExtension = false;
		if (supportedExtensionsFiles != null && !supportedExtensionsFiles.isEmpty()) {
			for (String definedExtension : supportedExtensionsFiles) {
				if (definedExtension.equalsIgnoreCase(extension)) {
					validExtension = true;
					break;
				}
			}
		}
		return validExtension;
	}

	public boolean isExtensionGalleryValid(String extension) {
		boolean validExtension = false;
		if (supportedExtensionsGallery != null && !supportedExtensionsGallery.isEmpty()) {
			for (String definedExtension : supportedExtensionsGallery) {
				if (definedExtension.equalsIgnoreCase(extension)) {
					validExtension = true;
					break;
				}
			}
		}
		return validExtension;
	}

	public List<String> getSupportedExtensionsGallery() {
		return supportedExtensionsGallery;
	}

	public void setSupportedExtensionsGallery(List<String> supportedExtensionsGallery) {
		this.supportedExtensionsGallery = supportedExtensionsGallery;
	}

	public List<String> getSupportedExtensionsFiles() {
		return supportedExtensionsFiles;
	}

	public void setSupportedExtensionsFiles(List<String> supportedExtensionsFiles) {
		this.supportedExtensionsFiles = supportedExtensionsFiles;
	}

}
