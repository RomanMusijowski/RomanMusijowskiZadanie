package com.zadanie.RomanMusijowskiZadanie.services;

import com.zadanie.RomanMusijowskiZadanie.models.Role;
import com.zadanie.RomanMusijowskiZadanie.models.RoleName;

public interface RoleService {
    void save(Role role);

    Role getByName(RoleName name);
}
