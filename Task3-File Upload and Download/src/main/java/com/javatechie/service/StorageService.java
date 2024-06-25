package com.javatechie.service;

import com.javatechie.entity.ImageData;
import com.javatechie.respository.StorageRepository;
import com.javatechie.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImages(List<MultipartFile> files, String userName, String userEmail) throws IOException {
        StringBuilder result = new StringBuilder();

        for (MultipartFile file : files) {
            ImageData imageData = repository.save(ImageData.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .userName(userName)
                    .userEmail(userEmail)
                    .build());
            if (imageData != null) {
                result.append("File uploaded successfully: ").append(file.getOriginalFilename()).append("\n");
            }
        }
        return result.toString();
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public List<ImageData> getAllImages() {
        return repository.findAll();
    }
}
