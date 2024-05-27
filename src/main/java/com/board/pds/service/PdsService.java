package com.board.pds.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;

// interface : 모두 다 abstract method
// abstract :  함수의 내용({})이 없는 함수 만들려면
// 여기 있는 애들은 다 나 코딩 안할거니까 니가 알아서 써 이런 느낌!
public interface PdsService {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

	PdsVo getPds(HashMap<String, Object> map);

	List<FilesVo> getFileList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map, MultipartFile[] uploadFiles);



}
