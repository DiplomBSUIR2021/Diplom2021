package com.diploma.demo.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "auth_users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String username;
    @Size(min = 2, message = "Не меньше 2 знаков")
    private String password;
    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        Set<Role> copy = roles.stream()
                .collect(Collectors.toSet());
        return copy;
    }

    public String getRolesAsString() {
        if (roles != null) {
            StringBuilder resultString = new StringBuilder();
            Iterator<Role> iterator = roles.iterator();
            while (iterator.hasNext()) {
                resultString.append(iterator.next().getName());
                if (iterator.hasNext()) {
                    resultString.append(", ");
                }
            }
            return resultString.toString();
        }
        return "";
    }

    public void setRoles(Set<Role> roles) {
        if (this.roles == null) {
            this.roles = roles;
        }
    }

    public void addRole(Role role) {
        if (this.roles != null && AuthUtils.authorizeRole("ROLE_ADMIN")) {
            this.roles.add(role);
        }
    }

    public boolean removeRole(Role role) {
        if (this.roles != null && AuthUtils.authorizeRole("ROLE_ADMIN")) {
            Iterator<Role> iterator = roles.iterator();
            while (iterator.hasNext()) {
                Role curRole = iterator.next();
                if (curRole.equals(role)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasRole(String roleName) {
        if (roles == null) {
            return false;
        }
        for (Role role : roles) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }
}