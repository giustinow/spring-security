package com.security.springsecurity.security.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ApplicationUserRole {
    GUEST(new HashSet<ApplicationUserPermission>()),
    STUDENT(new HashSet<ApplicationUserPermission>()),
    ADMIN(new HashSet<ApplicationUserPermission>(Arrays.asList(
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE
    )));

    private final Set<ApplicationUserPermission> userPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }
}
