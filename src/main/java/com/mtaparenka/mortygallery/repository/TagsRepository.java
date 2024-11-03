package com.mtaparenka.mortygallery.repository;

import com.mtaparenka.mortygallery.model.Tag;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface TagsRepository extends Repository<Tag, Long> {
    List<Tag> findAll();

    Optional<Tag> findByName(String name);

    Iterable<Tag> saveAll(Iterable<Tag> tags);
}
