package com.cognixia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.model.File;
import com.cognixia.model.LoginDetails;
import com.cognixia.model.ResponseData;
import com.cognixia.rabbitmq.sender.RabbitMQSender;
import com.cognixia.service.FileService;
import com.cognixia.service.UserService;

@RestController
public class FileController {
	
	private FileService fileService;
	
	@Autowired
    RabbitMQSender rabbitMQSender;
	
	@Autowired
	private UserService userService;
	
	int userid = 0;

	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
		File files = null;
		if(userid!=0) {
			files = fileService.saveFile(file, userid);
			String downloadURL = "";
			downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(files.getFileId()).toUriString();
			rabbitMQSender.send(files);
			return ResponseEntity.ok("Upload File Successful. Download url: " + downloadURL); 
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user. Please login to upload file.");
	}
	
	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception{
		File files = null;
		files = fileService.getFile(fileId);
		 return  ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(files.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + files.getFileName()
	                + "\"")
	                .body(new ByteArrayResource(files.getData()));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody LoginDetails loginDetails)
	{
		userid = userService.login(loginDetails);
		if(userid==0) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized user.");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Login Successful!");
	}
	
}
