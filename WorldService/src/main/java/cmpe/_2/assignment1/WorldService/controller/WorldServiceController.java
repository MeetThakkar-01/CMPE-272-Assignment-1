package cmpe._2.assignment1.WorldService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/world")
public class WorldServiceController {
	
	@GetMapping
	public String sayWorld() {
		return "World";
	} 
}
