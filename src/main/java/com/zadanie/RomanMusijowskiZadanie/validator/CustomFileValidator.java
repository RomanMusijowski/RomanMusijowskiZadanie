package com.zadanie.RomanMusijowskiZadanie.validator;

import com.zadanie.RomanMusijowskiZadanie.forms.UploadFilesForm;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomFileValidator implements Validator {
    public static final long TEN_MB_IN_BYTES = 10485760;
    @Override
    public boolean supports(Class<?> clazz) {
        return UploadFilesForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UploadFilesForm fileUploadModel = (UploadFilesForm)o;
        MultipartFile file = fileUploadModel.getFile();
        if(file.isEmpty()){
            errors.rejectValue("file", "upload.file.required");
        }
        else if(!checkType(file)){
            errors.rejectValue("file", "upload.invalid.file.type");
        }
        else if(file.getSize() > TEN_MB_IN_BYTES){
            errors.rejectValue("file", "upload.exceeded.file.size");
        }
    }

    private boolean checkType(MultipartFile file) {
        List<String> fileExtensions = Arrays.asList("JPEG", "JPG", "PNG");
        return fileExtensions.stream()
                .anyMatch(f -> f.equalsIgnoreCase(FilenameUtils.getExtension(file.getOriginalFilename())));
    }

}
