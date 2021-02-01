package com.zadanie.RomanMusijowskiZadanie.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    @NotEmpty
    @Size(min = 8, max = 60)
    private String username;
    @NotEmpty
    @Size(min = 4, max = 60)
    private String password;
}
