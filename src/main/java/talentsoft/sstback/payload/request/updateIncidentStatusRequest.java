package talentsoft.sstback.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class updateIncidentStatusRequest {
    private String status;
}
