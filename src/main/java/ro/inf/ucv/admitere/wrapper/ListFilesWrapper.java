package ro.inf.ucv.admitere.wrapper;

import java.util.ArrayList;
import java.util.List;

import ro.inf.ucv.admitere.entity.File;

public class ListFilesWrapper {

	private List<File> files;

	public ListFilesWrapper() {
		this.files = new ArrayList<File>();
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public void add(File file) {
		this.files.add(file);
	}
}
