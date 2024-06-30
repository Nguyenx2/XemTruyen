package com.example.xemtruyen.models;

import com.example.xemtruyen.models.bases.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Story extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long storyId;

    private String title;

    private String summary;

    private String status;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "stories_genres",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonManagedReference
    private Set<Genre> genres;

    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Chapter> chapters;


}
