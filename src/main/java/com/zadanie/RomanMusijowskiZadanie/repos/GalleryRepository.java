package com.zadanie.RomanMusijowskiZadanie.repos;

import com.zadanie.RomanMusijowskiZadanie.models.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Gallery findByUserId(Long userId);

    List<Gallery> findAllByUserId(Long userId);
}
