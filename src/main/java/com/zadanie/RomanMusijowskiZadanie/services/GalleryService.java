package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.Gallery;

import java.util.List;

public interface GalleryService {

    Gallery save(String name, String username);

    Gallery save(Gallery gallery);

    List<Gallery> findAll();

    Gallery findGalleryByUser(Long id);

    List<Gallery> findAllByUser(Long id);

    Gallery findById(Long id);

    void deleteById(Long id);
}
