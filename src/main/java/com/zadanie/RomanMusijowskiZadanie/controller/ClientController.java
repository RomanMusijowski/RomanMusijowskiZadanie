package com.zadanie.RomanMusijowskiZadanie.controller;

import com.zadanie.RomanMusijowskiZadanie.forms.UploadFilesForm;
import com.zadanie.RomanMusijowskiZadanie.models.Gallery;
import com.zadanie.RomanMusijowskiZadanie.services.GalleryService;
import com.zadanie.RomanMusijowskiZadanie.storage.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Controller
@RequestMapping(value = "/client")
@AllArgsConstructor
public class ClientController {

    private final GalleryService galleryService;
    private final StorageService storageService;

    @GetMapping(value = "/showPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public void showPicture(@RequestParam("path") String path, HttpServletResponse response) throws IOException {

        Resource resource = storageService.loadAsResource(path);
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

    @GetMapping("/show/{id}")
    public String showGallery(@PathVariable Long id, Model model){

        Gallery gallery = galleryService.findById(id);
        model.addAttribute("gallery", gallery);
        model.addAttribute("files", galleryService.findById(gallery.getId()).getPhotoSet());
        return "client/show";
    }
}
