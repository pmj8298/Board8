package com.board.pds.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;

// @Mapper : mybatis 와 연결하기 위해 존재
// PdsService에는 annotation 이 없고 PdsMapper에는 @Mapper 라는 annotation 이 있는 이유
@Mapper
public interface PdsMapper {

	List<PdsVo> getPdsList(HashMap<String, Object> map);

	PdsVo getPds(HashMap<String, Object> map);

	List<FilesVo> getfileList(HashMap<String, Object> map);

	void setWrite(HashMap<String, Object> map);

	void setFileWrite(HashMap<String, Object> map);

	void setReadCountUpdate(HashMap<String, Object> map);

	FilesVo getFileInfo(Long file_num);

	void deleteUploadFile(HashMap<String, Object> map);

	void setDelete(HashMap<String, Object> map);

	

	

}
