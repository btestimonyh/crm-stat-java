package dev.guarmo.crmstat.model.proj;

import dev.guarmo.crmstat.model.lead.Lead;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetProjectDto {
    private Long id;
    private String name;
    private String subs;
    private String ftd;
    private String rd;
    private String pixelId;
    private String buyerId;
    private List<Lead> leads;
}
