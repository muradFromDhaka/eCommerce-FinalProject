package com.example.PracticeCRUD.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public class FileStorageService {

	@Value("${file.upload-dir}")
	private String uploads;
	
	private Path uploadPath;
	
	@PostConstruct
	public void init() throws IOException{
		uploadPath = Paths.get(uploads);
		
		if(!Files.exists(uploadPath)){
			Files.createDirectories(uploadPath);
		}
	}
	
	
	public String saveFile(MultipartFile file) throws IOException{
		String fileName = UUID.randomUUID()+ "-" + file.getOriginalFilename();
		
		Path targetPath = uploadPath.resolve(fileName);
		Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		
		return targetPath.toString();
	}
	
	public void deleteFile(String file) throws IOException{
		Files.deleteIfExists(uploadPath.resolve(file));
	}
}
