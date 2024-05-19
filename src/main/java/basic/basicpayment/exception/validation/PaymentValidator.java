package basic.basicpayment.exception.validation;


import java.security.InvalidParameterException;

public class PaymentValidator {

    public static void validateAmount(Float amount) {
        if (amount == null || amount <= 0) {
            throw new InvalidParameterException("금액은 0보다 큰 숫자를 입력해야합니다.\n금액을 다시 입력해주세요.");
        }
    }

}
