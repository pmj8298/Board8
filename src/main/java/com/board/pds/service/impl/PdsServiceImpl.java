package com.board.pds.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;
import com.board.pds.mapper.PdsMapper;
import com.board.pds.service.PdsService;

@Service
public class PdsServiceImpl implements PdsService{
	@Autowired
	private PdsMapper pdsMapper;

	@Override
	public List<PdsVo> getPdsList(HashMap<String, Object> map) {
		// db 조회 결과 돌려준다 return
		List<PdsVo> pdsList = pdsMapper.getPdsList(map);
		System.out.println("pdsService pdsList:" + pdsList);
		return pdsList;
	}

	@Override
	public PdsVo getPds(HashMap<String, Object> map) {
		PdsVo pdsVo = pdsMapper.getPds(map);
		System.out.println("pdsVo:" + pdsVo);
		return pdsVo;
	}

	@Override
	public List<FilesVo> getFileList(HashMap<String, Object> map) {
		List<FilesVo> fileList = pdsMapper.getfileList(map);
		System.out.println("fileList:" + fileList);
		return fileList;
	}

}
