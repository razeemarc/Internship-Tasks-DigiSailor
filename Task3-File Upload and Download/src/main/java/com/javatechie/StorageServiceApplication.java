package com.javatechie;

import com.javatechie.entity.ImageData;
import com.javatechie.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/image")
public class StorageServiceApplication {

	@Autowired
	private StorageService service;

	@PostMapping
	public ResponseEntity<?> uploadImages(
			@RequestParam("images") List<MultipartFile> files,
			@RequestParam("userName") String userName,
			@RequestParam("userEmail") String userEmail) throws IOException {
		String uploadResult = service.uploadImages(files, userName, userEmail);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadResult);
	}

	@GetMapping("/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
		byte[] imageData = service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}

	@GetMapping("/all")
	public ResponseEntity<List<ImageData>> getAllImages() {
		List<ImageData> allImages = service.getAllImages();
		return ResponseEntity.status(HttpStatus.OK)
				.body(allImages);
	}

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceApplication.class, args);
	}
}
