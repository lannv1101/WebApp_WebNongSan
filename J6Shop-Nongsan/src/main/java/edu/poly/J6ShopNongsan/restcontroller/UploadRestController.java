package edu.poly.J6ShopNongsan.restcontroller;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.poly.J6ShopNongsan.service.UploadService;

@RestController
@CrossOrigin(origins = "*")
public class UploadRestController {
	
	@Autowired
	UploadService uploadService;
	
	@PostMapping("/lanmarket/api/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file,
			@PathVariable("folder") String folder) {
		File saveFile = uploadService.save(file,folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("name", saveFile.getName());
		node.put("size", saveFile.length());
		return node;
	}

}
