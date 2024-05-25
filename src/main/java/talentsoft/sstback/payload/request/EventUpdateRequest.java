package talentsoft.sstback.payload.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdateRequest {
        public String description;
        public String status;
}
