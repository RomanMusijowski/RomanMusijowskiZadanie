package com.zadanie.RomanMusijowskiZadanie.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFilesForm {

    private Long galleryId;

    private String photoName;

    private MultipartFile file;

    public UploadFilesForm(Long galleryId) {
        this.galleryId = galleryId;
    }
}
