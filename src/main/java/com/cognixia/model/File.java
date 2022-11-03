package com.cognixia.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@NoArgsConstructor
public class File implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@JsonProperty
	private String fileId;
	
	@JsonProperty
	private String fileName;
	
	@JsonProperty
	private String fileType;
	
	@Lob
	@JsonProperty
	private byte[] data;
	
	@JsonProperty
	private String uploadedTimestamp;
	
	@JsonProperty
	private int uploadedby;
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getUploadedTimestamp() {
		return uploadedTimestamp;
	}

	public void setUploadedTimestamp(String uploadedTimestamp) {
		this.uploadedTimestamp = uploadedTimestamp;
	}

	public int getUploadedby() {
		return uploadedby;
	}

	public void setUploadedby(int uploadedby) {
		this.uploadedby = uploadedby;
	}
	
	public File() {
		
	}

	public File(String fileName, String fileType, byte[] data, String uploadedTimestamp, int uploadedby) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.uploadedTimestamp = uploadedTimestamp;
		this.uploadedby = uploadedby;
	}

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", data="
				+ Arrays.toString(data) + "]";
	}
	
	
}
