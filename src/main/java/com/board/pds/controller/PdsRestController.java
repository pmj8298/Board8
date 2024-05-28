package com.board.pds.controller;

import java.io.File;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.pds.domain.FilesVo;
import com.board.pds.mapper.PdsMapper;

@RestController
public class PdsRestController {
	@Value("${part4.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;
	

	// "/deleteFile?file_num=3"
	@RequestMapping(value = "/deleteFile",
			        method = RequestMethod.GET,
			        headers = "Accept=application/json")
	public void deleteFile(@RequestParam HashMap<String,Object> map ) {
		// 실제 파일 삭제
		long file_num = Long.parseLong(String.valueOf( map.get("file_num")));
		FilesVo fileInfo = pdsMapper.getFileInfo(file_num);
		
		String sfile = fileInfo.getSfilename();
		File file = new File(uploadPath + sfile);
		if(file.exists())
		   file.delete();
		
		// files table 에서 file_num 으로 해당 파일 정보만 삭제
		pdsMapper.deleteUploadFileNum(map);
		
	}
}
