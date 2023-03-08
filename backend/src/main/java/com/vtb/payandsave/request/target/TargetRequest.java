package com.vtb.payandsave.request.target;

import com.vtb.payandsave.model.target.TargetPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@Getter
@Setter
@Data
public class TargetRequest {
    private String icon_id;
    @NotBlank(message = "Имя цели не должно быть пустым!")
    private String name;
    @PositiveOrZero(message = "Сумма цели должна быть положительной!")
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
