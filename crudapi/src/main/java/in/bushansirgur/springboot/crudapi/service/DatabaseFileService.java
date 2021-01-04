package in.bushansirgur.springboot.crudapi.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import in.bushansirgur.springboot.crudapi.exception.FileNotFoundException;
import in.bushansirgur.springboot.crudapi.exception.FileStorageException;
import in.bushansirgur.springboot.crudapi.model.DatabaseFile;
import in.bushansirgur.springboot.crudapi.repository.DatabaseFileRepository;

@Service
public class DatabaseFileService {

	@Autowired
	private DatabaseFileRepository dbFileRepository;

	public DatabaseFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			// throw new FileStorageException("Could not store file " + fileName + ". Please
			// try again!", ex);
			return null;
		}
		
	}

	public Object getFile(String fileId) throws IOException {
		return dbFileRepository.findById(fileId);

	}
}