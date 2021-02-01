package com.zadanie.RomanMusijowskiZadanie.init;

import com.zadanie.RomanMusijowskiZadanie.models.*;
import com.zadanie.RomanMusijowskiZadanie.repos.GalleryRepository;
import com.zadanie.RomanMusijowskiZadanie.services.GalleryService;
import com.zadanie.RomanMusijowskiZadanie.services.PhotoService;
import com.zadanie.RomanMusijowskiZadanie.services.RoleService;
import com.zadanie.RomanMusijowskiZadanie.services.UserService;
import com.zadanie.RomanMusijowskiZadanie.storage.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitDB implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final GalleryService galleryService;
    private final PhotoService photoService;
    private final GalleryRepository galleryRepository;
    private final StorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
        loadUsers();
        loadGalleries();
        storageService.deleteAll();
        storageService.init();
    }

    private void loadGalleries() {
        User admin = userService.findById(1L);
        User user = userService.findById(2L);


        Gallery gallery = new Gallery();
        gallery.setUser(admin);
        gallery.setName("Test1");
        gallery.setPhotoSet(Collections.emptySet());
        galleryRepository.save(gallery);

        Gallery gallery2 = new Gallery();
        gallery2.setUser(admin);
        gallery2.setName("Test2");
        gallery2.setPhotoSet(Collections.emptySet());
        galleryRepository.save(gallery2);

        Gallery gallery3 = new Gallery();
        gallery3.setUser(user);
        gallery3.setName("Test3");
        gallery3.setPhotoSet(Collections.emptySet());
        galleryRepository.save(gallery3);
    }

    private void loadUsers() {

        User admin = new User();
        admin.setUsername("admin");
        admin.setActive(true);
        admin.setPassword("admin");
        userService.saveAdmin(admin);

        User user = new User();
        user.setUsername("user");
        user.setActive(true);
        user.setPassword("user");
        userService.saveUser(user);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setActive(true);
        user2.setPassword("user2");
        userService.saveUser(user2);
    }

    private void loadRoles() {
        Role role = new Role();
        role.setName(RoleName.ROLE_ADMIN);
        roleService.save(role);

        Role role2 = new Role();
        role2.setName(RoleName.ROLE_USER);
        roleService.save(role2);
    }
}
