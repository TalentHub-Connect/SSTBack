package talentsoft.sstback.payload.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    private String description;
    private String status;
    private String dateEvent;
    private String place;
    private Integer typeeventid;
    private Integer companyid;
}
