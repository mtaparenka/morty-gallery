package com.mtaparenka.mortygallery.service;

import com.mtaparenka.mortygallery.client.CDNClient;
import com.mtaparenka.mortygallery.metadata.JpegMetadata;
import com.mtaparenka.mortygallery.model.Image;
import com.mtaparenka.mortygallery.model.Tag;
import com.mtaparenka.mortygallery.repository.ImageRepository;
import com.mtaparenka.mortygallery.repository.TagsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final TagsRepository tagsRepository;
    private final CDNClient cdnClient;

    public ImageService(ImageRepository imageRepository, TagsRepository tagsRepository, CDNClient cdnClient) {
        this.imageRepository = imageRepository;
        this.tagsRepository = tagsRepository;
        this.cdnClient = cdnClient;
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getImages(String[] tags) {
        return imageRepository.findByTagsNameIn(tags);
    }

    @Transactional
    public void uploadImage(byte[] imageData, String[] tags) {
        String uri = cdnClient.uploadImage(imageData);
        JpegMetadata metadata = JpegMetadata.read(imageData);
        LocalDateTime dateTimeTaken = LocalDateTime.parse(metadata.dateTime, DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss"));
        Set<Tag> tagsToSave = Arrays.stream(tags).map(s -> tagsRepository.findByName(s).orElse(new Tag(s))).collect(Collectors.toSet());
        Image image = new Image(uri, dateTimeTaken, LocalDateTime.now(), tagsToSave);

        imageRepository.save(image);
    }
}
