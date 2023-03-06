package com.vtb.payandsave.request.target;

import com.vtb.payandsave.model.target.TargetPriority;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetRequest {
    private String icon_id;
    private String name;
    private Float amount;
    private TargetPriority priority = TargetPriority.HIGH;
    private boolean isSuperPriority = false;

    @Override
    public String toString() {
        return "TargetRequest{" +
                "icon_id='" + icon_id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", priority=" + priority +
                ", isSuperPriority=" + isSuperPriority +
                '}';
    }
}
