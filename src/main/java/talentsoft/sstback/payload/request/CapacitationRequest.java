package talentsoft.sstback.payload.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapacitationRequest {
    private String description;
    private String capacitationDate;
    private String status;
    private String place;
    private Integer typeCapacitationId;
    private Integer companyid;
}
