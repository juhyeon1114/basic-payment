package basic.basicpayment.model.paymentDetails;

import basic.basicpayment.model.payment.Payment;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentDetailsDTO {

    private String cardNumber;
    private Integer expireMonth;
    private Integer expireYear;
    private String cvc;
    private Payment payment;

    public static PaymentDetailsDTO reqToDTO(PaymentDetailsApprovalRequest req, Payment payment) {
        return new PaymentDetailsDTO(
                req.getCardNumber(),
                req.getExpireMonth(),
                req.getExpireYear(),
                req.getCvc(),
                payment
        );
    }

}
