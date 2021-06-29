package jp.ac.hcs.s3a327.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//@Slf4j
@Controller
public class PortalController {
	
	@RequestMapping("/")
	public String index() {
//		log.info(null);
		return "index";
	}
	

}