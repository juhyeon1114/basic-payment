package basic.basicpayment.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeeUtils {

    @Getter
    private static final Float feePercentage = 0.03F;

    public static Float getFees(Float amount) {
        return amount * feePercentage;
    }

    public static Float addFee(Float amount) {
        return amount + getFees(amount);
    }


}
