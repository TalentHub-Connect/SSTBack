package talentsoft.sstback.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EventUpdateRequest {
        private String description;
        private String status;
        private String dateEvent;
        private String place;
        private Integer typeeventid;
}
