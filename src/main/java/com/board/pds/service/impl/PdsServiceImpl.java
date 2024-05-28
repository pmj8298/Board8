package com.board.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;
import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService{
	// 파일이 저장괼 경로(uploadPath <- applicaiton.properties)
	@Value("${part4.upload-path}")
	private String uploadPath;
	
	@Autowired
	private PdsMapper pdsMapper;

	// 자료실 목록 보기
	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		// map{"menu_id": "MENU01", "nowpage":1}
		// db 조회 결과 돌려준다 return
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);
		//System.out.println("pdsService pdsList:" + pdsList);
		return pdsList;
	}

	// 자료실 내용 보기
	@Override
	public PdsVo getPds(HashMap<String, Object> map) {
		PdsVo pdsVo = pdsMapper.getPds(map);
		//System.out.println("pdsVo:" + pdsVo);
		return pdsVo;
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		List<FilesVo> fileList = pdsMapper.getfileList(map);
		//System.out.println("fileList:" + fileList);
		return fileList;
	}

	// 자료실 글쓰기 저장
	@Override
	public void setWrite(HashMap<String, Object> map, MultipartFile[] uploadFiles) {
		System.out.println("1:" + map);
		// 자료실 글쓰기 + 파일 저장
		// 1. 파일 저장
		// uploadFiles [] 을 d:\dev\data 에 저장
		map.put("uploadPath", uploadPath);
		// PdsFile class - 파일 처리 전담 class 생성(
		// 1. 파일저장
		// 2. 저장된 파일정보 가져온다
		// map 1 : 1:{menu_id=MENU01, nowpage=1, title=sss, writer=ssss, content=ssss} uploadPath:D:/dev/data/uploadFiles length:3
		PdsFile.save(map, uploadFiles);
		
		// map 이 중요한 역할을 함
		// map 2 : 2:{menu_id=MENU01, nowpage=1, title=sss, writer=ssss, content=ssss, uploadPath=D:/dev/data/}
		// PdsFile.java에 map.put("aaa", 1234); 추가 -> map 2 : 2:{menu_id=MENU01, nowpage=1, title=ㅈㅈㅈ, writer=ㅈㅈㅈ, content=ㅈㅈㅈ, uploadPath=D:/dev/data/, aaa=1234}
		System.out.println("2:" + map);
		
		// db 저장----------------
		// 3. Board 에 글 저장
		pdsMapper.setWrite(map);
		
		// 4. Files 에 저장된 파일 정보를 저장 )
		List<FilesVo> fileList = (List<FilesVo>) map.get("fileList");
		if(fileList.size() != 0)
			pdsMapper.setFileWrite(map);
	}

	@Override
	public void setReadCountUpdate(HashMap<String, Object> map) {
		// 조회수 증가
		pdsMapper.setReadCountUpdate(map);
	}

}
