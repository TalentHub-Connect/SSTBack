package talentsoft.sstback.payload.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeIncidentRequest {
    private Integer id;
    private String nameIncident;
}
