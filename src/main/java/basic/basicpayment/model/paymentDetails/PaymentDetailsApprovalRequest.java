package basic.basicpayment.model.paymentDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsApprovalRequest {

    private String cardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])\\/(\\d{2})$", message = "옳바른 형식으로 만료기간을 입력해주세요.")
    private String expireDate;

    @Size(min = 3, max = 3, message = "CVC는 3자리여야 합니다.")
    private String cvc;

    protected Integer getExpireMonth() {
        String exp = getExpireDate();
        return exp == null
                ? 0
                : Integer.valueOf(exp.split("/")[0]);
    }

    protected Integer getExpireYear() {
        String exp = getExpireDate();
        return exp == null
                ? 0
                : Integer.valueOf(exp.split("/")[1]);
    }

}
