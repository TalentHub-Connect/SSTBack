package talentsoft.sstback.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CapacitationResponse {
    private Integer id;
    private String description;
    private String capacitationDate;
    private String status;
    private String place;
    private Integer typeCapacitationId;
}
