package basic.basicpayment.model.dto;


import basic.basicpayment.model.AppUser;
import basic.basicpayment.model.Balance;
import basic.basicpayment.model.common.BalanceCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceResponse {

    private Long userId;
    private Float balance;
    private BalanceCurrency currency;

    public static UserBalanceResponse createWithEntity(AppUser user, Balance balance) {
        UserBalanceResponse res = new UserBalanceResponse();
        res.setUserId(user.getId());
        res.setBalance(balance.getBalance());
        res.setCurrency(balance.getCurrency());
        return res;
    }

}
