package dev.guarmo.crmstat.model.lead;

import dev.guarmo.crmstat.model.SubStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetLeadDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String username;
    private String tgid;
    private String subid;
    private String status;
    private Boolean isFtd;
    private String rdCount;
    private String fbStatus;
    private String ftdTime;
    private String sub1;
    private String sub2;
    private String sub3;
    private String sub4;
    private String sub5;
    private String sub6;
    private String sub7;
    private String sub8;
    private String ftdAmount;
    private String rdAmount;
    private SubStatus subStatus;

    private String regDate;
    private String clickDate;

    private Long projectId;
}
