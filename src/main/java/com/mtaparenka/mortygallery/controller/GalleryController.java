package com.mtaparenka.mortygallery.controller;

import com.mtaparenka.mortygallery.model.Image;
import com.mtaparenka.mortygallery.model.ImagesRequest;
import com.mtaparenka.mortygallery.model.UploadRequest;
import com.mtaparenka.mortygallery.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class GalleryController {
    private final ImageService imageService;

    public GalleryController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getImages(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return imageService.getAllImages();
    }

    @PostMapping
    public List<Image> getImages(@RequestBody ImagesRequest imagesRequest) {
        return imageService.getImages(imagesRequest.tags());
    }

    @PostMapping("/upload")
    public void uploadImage(@RequestPart("file") MultipartFile file, @RequestPart("payload") UploadRequest uploadRequest) {
        try {
            imageService.uploadImage(file.getBytes(), uploadRequest.tags());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
