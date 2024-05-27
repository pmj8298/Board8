package com.board.pds.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.board.pds.domain.FilesVo;

public class PdsFile {

	// uploadPath 에 넘어온 file 들을 저장
	public static void save(
			HashMap<String, Object> map, 
			MultipartFile[] uploadFiles) {
		
		// 저장될 경로 가져온다
		String uploadPath = String.valueOf(map.get("uploadPath")); // object -> String
		  // String.valueOf : object -> String
	        // 제대로 작동 안함 - (String) map.get("uploadPath");
		//File dir = new File(uploadPath);
		System.out.println("uploadPath:" + uploadPath
				+"uploadFiles length:" + uploadFiles.length
				);
		
		List<FilesVo> filesVo = new ArrayList<>();
		
		for(MultipartFile uploadFile : uploadFiles) {
			
			String originalName = uploadFile.getOriginalFilename();
			System.out.println("originalName:" + originalName);
			// c:\download\data\abc.txt
			String fileName = originalName.substring(originalName.lastIndexOf("\\")+1); // abc.txt
			String fileExt = originalName.substring(originalName.lastIndexOf(".")); // .txt
			
			// d:\dev\data\2024\05\27
			// 날짜 폴더 생성- 중복 파일 방지가 목적
			String folderPath = makeFolder(uploadPath); 
			
			// 파일명 중복 방지- 같은 폴더에는 마지막 업로드된 파일만 저장
			// 중복하지 않는 고유한 문자열 생성: UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 이름을 저장
			// d:\dev\data(uploadPath) \(File.separator) 2024\05\27 \ uuid_data.txt
			String saveName =  uploadPath + File.separator 
					         + folderPath + File.separator 
					         + uuid + "_" + fileName;
			
			Path savePath = Paths.get(saveName);
			// java.nio.file.Path로 import(nio 는 network io)
			// Paths.get() : 특정 경로의 파일 정보를 가져온다
			
			// 파일 저장
			try {
				uploadFile.transferTo(savePath); // 업로드된 파일을 폴더에 저장
				System.out.println("저장됨");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} // save() end

	private static String makeFolder(String uploadPath) {
		// uploadPath  folderPath
		// d:\dev\data \2024\05\27
		String dateStr = LocalDate.now().format(
				DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// String folderPath= dateStr.replace("/", "\\"); -window
		String folderPath= dateStr.replace("/", File.separator);
		
		File uploadPathFolder = new File(uploadPath, folderPath);
		//System.out.println(uploadPathFolder.toPath().getFileSystem());
		System.out.println(uploadPathFolder.toString());
		
		if(uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs(); // mkdir : make directory
			// mkdir()  : 상위 폴더가 없으면 폴더 전체를 만들지 못한다 (x)
			// mkdirs() : 상위 폴더가 없어도 폴더 전체를 만들어준다
			
		}
		
		return folderPath;
	}
}
