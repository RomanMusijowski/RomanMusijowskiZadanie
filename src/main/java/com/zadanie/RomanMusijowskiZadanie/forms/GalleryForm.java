package com.zadanie.RomanMusijowskiZadanie.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleryForm {

    @NotEmpty
    @Size(min = 4, max = 60)
    private String galleryName;

    @NotEmpty
    @Size(min = 4, max = 60)
    private String username;
}
