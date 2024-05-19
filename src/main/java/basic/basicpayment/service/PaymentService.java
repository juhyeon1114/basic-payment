package basic.basicpayment.service;

import basic.basicpayment.model.*;
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

    public Payment create(PaymentDTO dto) {
        Payment entity = Payment.create(dto);
        return paymentRepository.saveAndFlush(entity);
    }

    public PaymentApprovalResponse paymentApproval(PaymentApprovalRequest req) {
        AppUser appUser = appUserRepository.findOneOrThrow(req.getUserId());
        Merchant merchant = merchantRepository.findOneOrThrow(req.getMerchantId());

        // Payment 생성
        Payment payment = create(PaymentDTO.reqToDto(req, merchant, appUser));

        // PaymentDetails 생성
        paymentDetailsService.create(PaymentDetailsDTO.reqToDTO(req.getPaymentDetails(), payment));

        return PaymentApprovalResponse.success(payment);
    }

}
