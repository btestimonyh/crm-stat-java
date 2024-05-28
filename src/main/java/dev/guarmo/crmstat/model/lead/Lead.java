package dev.guarmo.crmstat.model.lead;

import dev.guarmo.crmstat.model.SubStatus;
import dev.guarmo.crmstat.model.proj.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String tgid;
    private String subid;
    private String status;
    private Boolean isFtd;
    private SubStatus subStatus;
    private String rdCount;
    private String fbStatus;
    private String ftdAmount;
    private String rdAmount;
    private String regDate;
    private String clickDate;
    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String sub6;
    private String sub7;
    private String sub8;

    @ManyToOne
    private Project project;

    public Lead(Long id) {
        this.id = id;
    }
}
