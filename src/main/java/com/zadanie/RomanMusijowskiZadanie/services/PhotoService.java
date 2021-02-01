package com.zadanie.RomanMusijowskiZadanie.services;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    public void save(MultipartFile file, Long galleryId, String fileName);
}
