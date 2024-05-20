package basic.basicpayment.utils;

import basic.basicpayment.model.common.BalanceCurrency;
import lombok.extern.slf4j.Slf4j;

import static basic.basicpayment.model.common.BalanceCurrency.USD;

@Slf4j
public class MoneyUtils {

    public static Float floorMoney(Float money, BalanceCurrency currency) {
        if (USD.equals(currency)) {
            return (float) Math.floor(money * 100) / 100.0F;
        } else {
            return (float) Math.floor(money);
        }
    }

}