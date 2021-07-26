package com.security.springsecurity.security.constants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {
    GUEST(new HashSet<>()),
    STUDENT(new HashSet<>()),
    ADMIN(new HashSet<>(Arrays.asList(
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE
    )));

    private final Set<ApplicationUserPermission> userPermissions;

    ApplicationUserRole(Set<ApplicationUserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<ApplicationUserPermission> getUserPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = getUserPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return grantedAuthorities;
    }
}
