package basic.basicpayment.utils;

import basic.basicpayment.model.common.BalanceCurrency;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static basic.basicpayment.model.common.BalanceCurrency.KRW;
import static basic.basicpayment.model.common.BalanceCurrency.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MoneyUtilsTest {

    @Test
    @DisplayName("금액 버림")
    void 금액_버림() throws Exception {
        Float krw = MoneyUtils.floorMoney(100.123123F, KRW);
        String krwString = String.valueOf(krw);
        log.info(krwString);
        assertEquals("100.0", krwString);

        Float usd = MoneyUtils.floorMoney(100.123123F, USD);
        String usdString = String.valueOf(usd);
        log.info(usdString);
        assertEquals("100.12", usdString);
    }

}