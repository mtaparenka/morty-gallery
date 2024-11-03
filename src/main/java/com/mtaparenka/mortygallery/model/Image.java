package com.mtaparenka.mortygallery.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String uri;
    public LocalDateTime dateTaken;
    public LocalDateTime dateUploaded;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "image_tags",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    public Set<Tag> tags;

    public Image() {
    }

    public Image(String uri, LocalDateTime dateTaken, LocalDateTime dateUploaded, Set<Tag> tags) {
        this.uri = uri;
        this.dateTaken = dateTaken;
        this.dateUploaded = dateUploaded;
        this.tags = tags;
    }
}
