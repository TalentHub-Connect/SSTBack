package talentsoft.sstback.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIncidentRequest {
    private String description;
    private String status;
}
