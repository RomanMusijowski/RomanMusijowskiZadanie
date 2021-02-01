package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.Gallery;
import com.zadanie.RomanMusijowskiZadanie.models.Photo;
import com.zadanie.RomanMusijowskiZadanie.repos.PhotoRepository;
import com.zadanie.RomanMusijowskiZadanie.storage.StorageService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final StorageService storageService;
    private final GalleryService galleryService;

    @Override
    public void save(MultipartFile file, Long galleryId, String fileName) {
        Gallery gallery = galleryService.findById(galleryId);
        Photo photo = new Photo(null, fileName, storageService.store(file, fileName), gallery);
        gallery.getPhotoSet().add(photo);

        photoRepository.save(photo);
//        galleryService.save(gallery);

    }


    private String generateName(){
        return RandomStringUtils.randomAlphabetic(5);
    }
}
