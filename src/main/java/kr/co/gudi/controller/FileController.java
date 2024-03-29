package kr.co.gudi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.gudi.service.FileService;

@Controller
public class FileController {
	
	/*
		저장한 사진을 보기 위해서는 server.xml 에 아래 내용을 추가해 줘야 한다.
		<!-- /photo 라는 요청이 들어오면 C:/upload 로 연결해라 -->
		<Context docBase="C:/upload" path="/photo"/>
	 */
	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired FileService service;
	
	@RequestMapping(value="/")
	public String home() {
		logger.info("index 페이지 요청");
		return "index";
	}
	
	// 일반 파라메터는 MultipartFile 보다 뒤에 있어야 한다.
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	public String upload(MultipartFile uploadFile, @RequestParam String title) {		
		logger.info("title : "+title);
		logger.info("file : "+uploadFile.getOriginalFilename());
		service.upload(uploadFile);		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/multiUpload", method=RequestMethod.POST)
	public String multiUpload(MultipartFile[] files) {
		logger.info("file length : "+files.length);
		service.multiUpload(files);		
		return "redirect:/list";
	}	
	
	@RequestMapping(value="/list")
	public String list(Model model) {		
		List<String> list = service.list();
		model.addAttribute("list", list);		
		return "list";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam String file) {
		logger.info("file name : "+file);
		service.delete(file);
		return "redirect:/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
