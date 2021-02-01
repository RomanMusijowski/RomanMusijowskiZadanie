package com.zadanie.RomanMusijowskiZadanie.controller;

import com.zadanie.RomanMusijowskiZadanie.forms.GalleryForm;
import com.zadanie.RomanMusijowskiZadanie.forms.UploadFilesForm;
import com.zadanie.RomanMusijowskiZadanie.models.Gallery;
import com.zadanie.RomanMusijowskiZadanie.models.User;
import com.zadanie.RomanMusijowskiZadanie.services.GalleryService;
import com.zadanie.RomanMusijowskiZadanie.services.PhotoService;
import com.zadanie.RomanMusijowskiZadanie.services.UserService;
import com.zadanie.RomanMusijowskiZadanie.storage.StorageService;
import com.zadanie.RomanMusijowskiZadanie.validator.CustomFileValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping(value = "/admin")
@AllArgsConstructor
public class AdminController {

    private final GalleryService galleryService;
    private final PhotoService photoService;
    private final StorageService storageService;
    private final UserService userService;
    private final CustomFileValidator customFileValidator;

    @GetMapping("/new")
    public String newLesson(Model model){
        List<String> names = userService.findAll().stream()
                .map(User::getUsername)
                .collect(Collectors.toList());

        model.addAttribute("names", names);
        model.addAttribute("galleryForm", new GalleryForm());
        return "admin/galleryForm";
    }

    @PostMapping
    public String create(@Valid GalleryForm galleryForm, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){

            List<String> names = userService.findAll().stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            model.addAttribute("names", names);
            return "admin/galleryForm";
        }
        Gallery gallery = galleryService.save(galleryForm.getGalleryName(), galleryForm.getUsername());
        return "redirect:show/" + gallery.getId();
    }

    @GetMapping("/show/{id}")
    public String showGallery(@PathVariable Long id, Model model){

        Gallery gallery = galleryService.findById(id);
        model.addAttribute("gallery", gallery);
        model.addAttribute("uploadForm", new UploadFilesForm(gallery.getId()));
        model.addAttribute("files", galleryService.findById(gallery.getId()).getPhotoSet());
        return "admin/show";
    }

    @GetMapping(value = "/showPicture", produces = MediaType.IMAGE_JPEG_VALUE)
    public void showPicture(@RequestParam("path") String path, HttpServletResponse response) throws IOException {

        Resource resource = storageService.loadAsResource(path);
        StreamUtils.copy(resource.getInputStream(), response.getOutputStream());
    }

    @PostMapping("/uploadFile")
    public String uploadFiles(@Valid UploadFilesForm uploadFileForm, BindingResult bindingResult, Model model) {

        customFileValidator.validate(uploadFileForm, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("uploadFileForm", uploadFileForm);
            return "redirect:show/" + uploadFileForm.getGalleryId();
        }

        photoService.save(uploadFileForm.getFile(), uploadFileForm.getGalleryId(), uploadFileForm.getPhotoName());
        model.addAttribute("uploadFileForm", uploadFileForm);

        return "redirect:show/" + uploadFileForm.getGalleryId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        galleryService.deleteById(id);
        return "redirect:/";
    }

}
