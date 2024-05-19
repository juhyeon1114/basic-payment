package basic.basicpayment.model.balance;

import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.common.BalanceCurrency;
import lombok.*;

@Getter
@AllArgsConstructor
public class BalanceDTO {

    private Float balance;
    private BalanceCurrency currency;
    private AppUser appUser;

    public static BalanceDTO create(
            Float balance,
            BalanceCurrency currency,
            AppUser appUser
    ) {
        return new BalanceDTO(
                balance,
                currency,
                appUser
        );
    }

}
