package dev.guarmo.crmstat.model.user;

import dev.guarmo.crmstat.model.PermissionStatus;
import dev.guarmo.crmstat.model.Status;
import dev.guarmo.crmstat.model.proj.Project;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SoftDelete
public class CrmUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(unique = true)
    private String login;
    private String telegram;
    private String tag;
    private String password;
    private Status role;
    private PermissionStatus permissionStatus;
//    @CreationTimestamp
//    @Column(updatable = false)
    private String registrationDate;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Project> projects;

    public CrmUser(String id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
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
        return permissionStatus == PermissionStatus.ACTIVE;
    }
}
