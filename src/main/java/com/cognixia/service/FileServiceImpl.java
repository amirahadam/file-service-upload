package com.cognixia.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cognixia.model.File;
import com.cognixia.repository.FileRepository;

@Service
public class FileServiceImpl implements FileService {
	
	private FileRepository fileRepository;
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public FileServiceImpl(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}


	@Override
	public File saveFile(MultipartFile file, int userid) throws Exception{
		String fileName= StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new Exception("Filename Error");
			}
			Date datetime = new Date();
			File files = new File(fileName,file.getContentType(),file.getBytes(), format.format(datetime), userid);
			return fileRepository.save(files);
		}catch(Exception e) {
			throw new Exception("Could not save file: " + fileName);
		}
	}


	@Override
	public File getFile(String fileId) throws Exception {
		// TODO Auto-generated method stub
		return fileRepository.findById(fileId).orElseThrow(() -> new Exception("File not found with Id: " + fileId));
	}

	@Override
	public List<File> listFiles() throws Exception {
		// TODO Auto-generated method stub
		return fileRepository.findAll();
	}
}
