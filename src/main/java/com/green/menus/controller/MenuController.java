package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MenuController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:8080/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list( Model model ) {
		// 조회한결과를 ArrayList 로 돌려준다
		List<MenuDTO> menuList = menuMapper.getMenuList(); // ArrayList 결과를 받는다
		System.out.println(menuList);
		
		model.addAttribute("msg", "하하");
		model.addAttribute("menuList", menuList);
		
		return "menus/list";	// /WEB-INF/views/ menus/list .jsp
	}
	
	// /Menus/WriteForm
	@RequestMapping("/Menus/WriteForm")
	public String writeForm() {
		return "menus/write";
	}

	// /Menus/Write
	@RequestMapping("/Menus/Write")
//	public String write(String menu_id, String menu_name, int menu_seq) {
	public String write(MenuDTO menuDTO, Model model) {
		
		// 넘어온 값
		System.out.println("menu_id="   + menuDTO.getMenu_id());
		System.out.println("menu_name=" + menuDTO.getMenu_name());
		System.out.println("menu_seq="  + menuDTO.getMenu_seq());
		
		// db에 저장
		menuMapper.insertMenu( menuDTO );
		
		// 다시 조회 -> menuList
		List<MenuDTO> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
		
		return "home";
	}
}













