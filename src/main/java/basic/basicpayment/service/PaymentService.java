package basic.basicpayment.service;

import basic.basicpayment.exception.validation.PaymentValidator;
import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.common.PaymentStatus;
import basic.basicpayment.model.merchant.Merchant;
import basic.basicpayment.model.payment.PaymentApprovalRequest;
import basic.basicpayment.model.dto.PaymentApprovalResponse;
import basic.basicpayment.model.payment.Payment;
import basic.basicpayment.model.payment.PaymentDTO;
import basic.basicpayment.model.paymentDetails.PaymentDetailsDTO;
import basic.basicpayment.repository.appUser.AppUserRepository;
import basic.basicpayment.repository.merchant.MerchantRepository;
import basic.basicpayment.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final PaymentDetailsService paymentDetailsService;
    private final PaymentRepository paymentRepository;
    private final BalanceService balanceService;

    public Payment create(PaymentDTO dto) {
        Payment entity = Payment.create(dto);
        return paymentRepository.save(entity);
    }

    public PaymentApprovalResponse paymentApproval(PaymentApprovalRequest req) {
        Payment payment;
        AppUser appUser = appUserRepository.findOneOrThrow(req.getUserId());
        Merchant merchant = merchantRepository.findOneOrThrow(req.getMerchantId());

        // Payment 생성
        payment = create(PaymentDTO.reqToDto(req, merchant, appUser));
        try {
            // Validation
            PaymentValidator.validateAmount(payment.getAmount());

            // PaymentDetails 생성
            paymentDetailsService.create(PaymentDetailsDTO.reqToDTO(req.getPaymentDetails(), payment));

            // Payment 상태 변경
            payment.approved();
        } catch (Exception e) {
            payment.failed(e.getMessage());
        }

        if (PaymentStatus.approved.equals(payment.getStatus())) {
            // Balance 생성 및 수정
            balanceService.createOrUpdateBalance(appUser.getId(), req.amountTotal(), req.getCurrency());
        }

        return PaymentApprovalResponse.createWithEntity(payment);
    }

}
