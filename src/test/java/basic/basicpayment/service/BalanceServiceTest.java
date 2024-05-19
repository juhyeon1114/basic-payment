package basic.basicpayment.service;

import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.balance.Balance;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.repository.appUser.AppUserRepository;
import basic.basicpayment.repository.balance.BalanceRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BalanceServiceTest {

    Long testUserId;

    @Autowired BalanceRepository balanceRepository;
    @Autowired AppUserRepository appUserRepository;
    @Autowired BalanceService balanceService;

    @BeforeAll
    void beforeAll() {
        AppUser saved = appUserRepository.save(AppUser.create("hi@bye.com"));
        testUserId = saved.getId();
    }

    @AfterAll
    void afterAll() {
        appUserRepository.deleteById(testUserId);
    }

    @Test
    @DisplayName("밸런스_생성_및_수정")
    void 밸런스_생성_및_수정() throws Exception {
        Balance balance = balanceRepository.findOneByUserIdAndCurrency(testUserId, BalanceCurrency.KRW);
        Assertions.assertNull(balance, "초기 Balance는 존재하지 않음");

        balanceService.createOrUpdateBalance(testUserId, 100F, BalanceCurrency.KRW);

        log.info("생성 테스트");
        Balance createdBalance = balanceRepository.findOneByUserIdAndCurrency(testUserId, BalanceCurrency.KRW);
        Assertions.assertNotNull(createdBalance, "Balance 생성 여부 체크");
        Assertions.assertEquals(BalanceCurrency.KRW, createdBalance.getCurrency(), "Balance의 Currency 체크");
        Assertions.assertEquals(100F, createdBalance.getBalance(), "Balance가 잘 입력됐는지 체크");

        log.info("업데이트 테스트");
        balanceService.createOrUpdateBalance(testUserId, 100F, BalanceCurrency.KRW);
        Balance updatedBalance = balanceRepository.findOneByUserIdAndCurrency(testUserId, BalanceCurrency.KRW);
        Assertions.assertEquals(200F, updatedBalance.getBalance(), "Balance가 업데이트 잘됐는지 체크");
    }

}