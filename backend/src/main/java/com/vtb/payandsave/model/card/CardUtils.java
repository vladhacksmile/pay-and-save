package com.vtb.payandsave.model.card;

public class CardUtils {
    /**
     * Метод для округления трат
     * @param roundingStep шаг округления
     * @param amount сумма, по которой нужно произвести округление
     * @return результат округления в большую сторону
     */
    public static float calculateRoundingByAmount(int roundingStep, float amount) {
        float remainder = amount % roundingStep;
        return remainder != 0 ? roundingStep - remainder : 0;
    }
}
