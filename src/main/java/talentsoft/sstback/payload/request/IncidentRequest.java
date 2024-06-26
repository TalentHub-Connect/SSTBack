package talentsoft.sstback.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class IncidentRequest {
    private String description;
    private String incidentdate;
    private String status;
    private Integer typeincidentid;
    private Integer employeeid;
    private Integer companyid;
}
