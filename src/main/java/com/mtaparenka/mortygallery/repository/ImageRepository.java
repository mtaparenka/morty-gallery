package com.mtaparenka.mortygallery.repository;

import com.mtaparenka.mortygallery.model.Image;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ImageRepository extends Repository<Image, Long> {
    @EntityGraph(attributePaths = "tags")
    List<Image> findAll();

    @EntityGraph(attributePaths = "tags")
    List<Image> findByTagsNameIn(String[] tags);

    void save(Image image);
}
