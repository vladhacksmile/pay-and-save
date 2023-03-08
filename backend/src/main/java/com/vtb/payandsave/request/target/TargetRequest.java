package com.vtb.payandsave.request.target;

import com.vtb.payandsave.model.target.TargetPriority;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetRequest {
    private String icon_id;
    @NotBlank(message = "Имя цели не должно быть пустым!")
    private String name;
    @Min(value = 1, message = "Сумма цели должна быть минимум 1!")
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
