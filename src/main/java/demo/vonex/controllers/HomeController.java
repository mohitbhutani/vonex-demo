package demo.vonex.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.vonex.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
	@Autowired
	HomeService homeService;
	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping("/")
	public String index() {
		return "/index";
	}

	@RequestMapping("/getAnswer")
	@ResponseBody
	public String getAnswer() throws JsonProcessingException {
		return objectMapper.writeValueAsString(homeService.getAnswerDTO(homeService.getInitialRequest()));
	}

	@RequestMapping("/index")
	public String start() {
		return "/index";
	}

}
