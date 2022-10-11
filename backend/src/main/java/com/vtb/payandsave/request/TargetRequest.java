package com.vtb.payandsave.request;

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
    private Long amount;
    private TargetPriority priority;
}
