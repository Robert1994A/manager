package ro.inf.ucv.admitere.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.admitere.entity.File;
import ro.inf.ucv.admitere.repository.FileRepository;

@Service
@Transactional
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public File save(File file) {
		return fileRepository.save(file);
	}
}
