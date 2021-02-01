package com.zadanie.RomanMusijowskiZadanie.models;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "GALLERIES")
@NoArgsConstructor
@AllArgsConstructor
public class Gallery {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "gallery")
    private Set<Photo> photoSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gallery gallery = (Gallery) o;
        return Objects.equals(id, gallery.id) &&
                Objects.equals(name, gallery.name) &&
                Objects.equals(photoSet, gallery.photoSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photoSet);
    }
}
