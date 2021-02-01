package com.zadanie.RomanMusijowskiZadanie.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "PHOTOS")
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "URL", unique = true, nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name="GALLERY_ID")
    @ToString.Exclude
    private Gallery gallery;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(name, photo.name) &&
                Objects.equals(url, photo.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}
