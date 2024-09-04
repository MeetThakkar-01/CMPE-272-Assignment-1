package cmpe._2.assignment1.HelloWorldService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/helloworld")
public class HelloWorldServiceController {
	
	@GetMapping
	public String sayHelloWorld() {
		RestTemplate rs = new RestTemplate();
		
		String s1 = rs.getForObject("http://hello-service:8081/hello", String.class);
		String s2 = rs.getForObject("http://world-service:8082/world", String.class);
		
		return s1 + " " + s2;
	}
}
