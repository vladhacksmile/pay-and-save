package com.vtb.payandsave.request.target;

import com.vtb.payandsave.model.target.TargetPriority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TargetRequest {
    private String icon_id;
    private String name;
    private Float amount;
    private TargetPriority priority;
    private boolean isSuperPriority;
}
