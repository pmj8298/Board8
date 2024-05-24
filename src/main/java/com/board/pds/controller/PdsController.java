package com.board.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.pds.domain.FilesVo;
import com.board.pds.domain.PdsVo;
import com.board.pds.service.PdsService;

@Controller
@RequestMapping("/Pds")
public class PdsController {
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private PdsService pdsService;
	
	// /Pds/List?nowpage=1&menu_id=MENU01
	// 파라미터를 받는 vo 가 없으므로 HashMap 이용해서 파라미터 처리, 모든 것을 map 으로 던져서 처리할 수 있어서 가장 편함 -> vo 보다 편함
	// HashMap 으로 인자 처리 할 때에는 @RequestParam 필수
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String,Object> map) {
		System.out.println("map:" + map); // map:{nowpage=1, menu_id=MENU01}
		
		// 메뉴목록
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		// 자료실목록
		List<PdsVo> pdsList = pdsService.getPdsList(map); // 정확히는 menu_id를 가져오는 것
		
		ModelAndView mv = new ModelAndView();
		// 메뉴목록
		mv.addObject("menuList", menuList);
		// 자료실목록 Board + Files
		mv.addObject("pdsList", pdsList); 
		mv.addObject("map", map); // map 안에는 아무거나 넣을 수 있음 왜냐 KEY 형태로 객체를 추가할 수 있기때무니지
		mv.setViewName("pds/list"); // pds/list.jsp -> model 에서 이동할 페이지를 담음
		return mv;
	}
	// http://localhost:9090/Pds/View?bno=1000&nowpage=1&menu_id=MENU01
	@RequestMapping("/View")
	public ModelAndView view(@RequestParam HashMap<String,Object> map) {
		// 메뉴목록
		List<MenuVo> menuList = menuMapper.getMenuList();
		// 조회수 증가
		// 조회할 자료실의 게시물 정보 : Board -> PdsVo
		PdsVo pdsVo = pdsService.getPds(map);
		// 조회할 파일정보 : FilesVo -> PdsVo
		// Bno에 해당되는 파일들의 정보
		List<FilesVo> fileList = pdsService.getFileList(map);
		ModelAndView mv = new ModelAndView();
		mv.addObject("menuList",menuList);
		mv.addObject("pdsVo",pdsVo);
		mv.addObject("fileList",fileList);
		mv.addObject("map",map); // map
		
		mv.setViewName("pds/view");
		return mv;
	}
	
	// 자료실 새 글 등록 -파일 업로드 포함
	// /Pds/WriteForm?nowpage=1&menu_id=MENU01
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm(@RequestParam HashMap<String,Object> map) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("map",map);
		mv.setViewName("pds/write");
		
		return mv;
	}
}
