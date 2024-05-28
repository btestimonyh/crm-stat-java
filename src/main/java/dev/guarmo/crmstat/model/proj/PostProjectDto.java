package dev.guarmo.crmstat.model.proj;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostProjectDto {
    private String name;
    private String subs;
    private String ftd;
    private String rd;
    private String buyerId;
    private String pixelId;
}
