package basic.basicpayment.exception.validation;


import basic.basicpayment.exception.exceptions.ItemNotFoundException;

public class ItemValidator {

    public static <T> void throwNFE(T text) {
        String message = text + "을(를) 찾을 수 없습니다.";
        throw  new ItemNotFoundException(message);
    }

    public static <T, S> void validateNotFound(T item, S s) {
        if (item == null) {
            throwNFE(s);
        }
    }


}
