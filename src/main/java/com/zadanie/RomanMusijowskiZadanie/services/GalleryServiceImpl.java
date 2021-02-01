package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.Gallery;
import com.zadanie.RomanMusijowskiZadanie.repos.GalleryRepository;
import com.zadanie.RomanMusijowskiZadanie.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final UserService userService;
    private final StorageService storageService;

    @Override
    public Gallery save(String name, String username) {
        Gallery gallery = new Gallery();
        gallery.setName(name);
        gallery.setUser(userService.findByUsername(username));
        gallery.setPhotoSet(Collections.emptySet());

        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery save(Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @Override
    public List<Gallery> findAll() {
        return galleryRepository.findAll();
    }

    @Override
    public Gallery findGalleryByUser(Long id) {
        return galleryRepository.findByUserId(id);
    }

    @Override
    public List<Gallery> findAllByUser(Long id) {
        return galleryRepository.findAllByUserId(id);
    }

    @Override
    public Gallery findById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gallery with id " + id + " not found."));
    }

    @Override
    public void deleteById(Long id) {
        Gallery gallery = galleryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gallery with id " + id + " not found."));

        gallery.getPhotoSet().forEach(photo -> storageService.deleteFile(photo.getUrl()));
        galleryRepository.deleteById(id);
    }
}
