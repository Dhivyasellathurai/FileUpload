package com.example.fileUpload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.fileUpload.entity.FileUpload;
import com.example.fileUpload.repository.FileUploadRepository;

@Service
public class FileUploadService {

	@Autowired
	private FileUploadRepository fileUploadRepository;

	private String folderName;
 
	private final String FOLDER_PATH = "C:\\springboot project\\fileUpload\\"+folderName;

	
	
//	Path uploadPath = Paths.get("Files-Upload" + "\\" + folderName);

	public String uploadImageToFileSystem(MultipartFile file) throws IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();


		FileUpload fileData = fileUploadRepository.save(FileUpload.builder().name(file.getOriginalFilename())
				.type(file.getContentType()).filePath(filePath).build());

		file.transferTo(new File(filePath));

		if (fileData != null) {
			return "file uploaded successfully : " + filePath;
		}
		return null;
	}

	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		Optional<FileUpload> fileData = fileUploadRepository.findByName(fileName);
		String filePath = fileData.get().getFilePath();
		byte[] images = Files.readAllBytes(new File(filePath).toPath());
		return images;
	}

}
