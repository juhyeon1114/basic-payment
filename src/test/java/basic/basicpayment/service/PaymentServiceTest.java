package basic.basicpayment.service;

import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.common.PaymentStatus;
import basic.basicpayment.model.dto.PaymentApprovalResponse;
import basic.basicpayment.model.merchant.Merchant;
import basic.basicpayment.model.payment.Payment;
import basic.basicpayment.model.payment.PaymentApprovalRequest;
import basic.basicpayment.model.paymentDetails.PaymentDetails;
import basic.basicpayment.model.paymentDetails.PaymentDetailsApprovalRequest;
import basic.basicpayment.repository.appUser.AppUserRepository;
import basic.basicpayment.repository.balance.BalanceRepository;
import basic.basicpayment.repository.merchant.MerchantRepository;
import basic.basicpayment.repository.payment.PaymentRepository;
import basic.basicpayment.repository.paymentDetails.PaymentDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentServiceTest {

    Long testUserId;
    String testMerchantId;
    PaymentDetailsApprovalRequest detailsReq = new PaymentDetailsApprovalRequest(
            "c-a-r-d-n-u-m-b-e-r",
            "1/01",
            "123"
    );

    @Autowired BalanceRepository balanceRepository;
    @Autowired AppUserRepository appUserRepository;
    @Autowired MerchantRepository merchantRepository;
    @Autowired BalanceService balanceService;
    @Autowired PaymentService paymentService;
    @Autowired PaymentDetailsRepository paymentDetailsRepository;
    @Autowired PaymentRepository paymentRepository;

    @BeforeAll
    void beforeAll() {
        AppUser saved = appUserRepository.save(AppUser.create("hi@bye.com"));
        testUserId = saved.getId();

        Merchant merchant = merchantRepository.save(Merchant.create("test merchant"));
        testMerchantId = merchant.getId();
    }

    @AfterAll
    void afterAll() {
        appUserRepository.deleteById(testUserId);
    }

    @Test
    @DisplayName("결제승인_성공")
    void 결제승인_성공() {
        PaymentApprovalRequest approvalReq = new PaymentApprovalRequest(
                testUserId,
                100F,
                BalanceCurrency.KRW,
                testMerchantId,
                PaymentMethod.creditCard,
                detailsReq
        );

        PaymentApprovalResponse response = paymentService.paymentApproval(approvalReq);

        String paymentId = response.getPaymentId();
        assertNotNull(paymentId);
        assertEquals(PaymentStatus.approved, response.getStatus(), "성공 여부 체크");

        PaymentDetails paymentDetails = paymentDetailsRepository.findOneByPaymentId(paymentId);
        assertNotNull(paymentDetails, "PaymentDetails 생성 체크");
        assertEquals(detailsReq.getCardNumber(), paymentDetails.getCardNumber());
        assertEquals(detailsReq.getCvc(), paymentDetails.getCvc());
        assertEquals("01/01", paymentDetails.getExpireDate());
    }

    @Test
    @DisplayName("결제승인_실패")
    void 결제승인_실패() {

        PaymentApprovalRequest approvalReq = new PaymentApprovalRequest(
                testUserId,
                0F,
                BalanceCurrency.KRW,
                testMerchantId,
                PaymentMethod.creditCard,
                detailsReq
        );

        PaymentApprovalResponse response = paymentService.paymentApproval(approvalReq);

        String paymentId = response.getPaymentId();
        assertNotNull(paymentId);
        assertEquals(PaymentStatus.failed, response.getStatus(), "실패 여부 체크");

        Payment payment = paymentRepository.findOneOrThrow(paymentId);
        log.info("payment.getReason()={}", payment.getReason());
        assertNotNull(payment.getReason());

    }
}