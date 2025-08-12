package in.ratansgh.models.user;

import in.ratansgh.models.enums.UserRole;

public class SystemAdmin extends User {
    public SystemAdmin(String id, String name) {
        super(id, name, UserRole.SYSTEM_ADMIN);
    }
}
