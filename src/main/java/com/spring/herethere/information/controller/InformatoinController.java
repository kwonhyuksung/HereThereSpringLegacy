package com.spring.herethere.information.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformatoinController {

	@RequestMapping(value = "/information/mvList.do", method = RequestMethod.GET)
	public ModelAndView informationList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/information/list");
		return mav;
	}

	@RequestMapping(value = "/information/mvView.do", method = RequestMethod.GET)
	public ModelAndView informationView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/information/view");
		return mav;
	}
}
