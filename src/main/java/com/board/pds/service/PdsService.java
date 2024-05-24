package com.board.pds.service;

import java.util.HashMap;
import java.util.List;

import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;


public interface PdsService {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

	PdsVo getPds(HashMap<String, Object> map);

	List<FilesVo> getFileList(HashMap<String, Object> map);



}
