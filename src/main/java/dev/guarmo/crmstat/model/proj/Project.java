package dev.guarmo.crmstat.model.proj;

import dev.guarmo.crmstat.model.user.CrmUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subs;
    private String ftd;
    private String rd;
    private String pixelId;
    @ManyToOne
    private CrmUser crmUser;

    public Project(Long id) {
        this.id = id;
    }
}
