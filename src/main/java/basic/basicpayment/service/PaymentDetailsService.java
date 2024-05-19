package basic.basicpayment.service;

import basic.basicpayment.model.paymentDetails.PaymentDetails;
import basic.basicpayment.model.paymentDetails.PaymentDetailsDTO;
import basic.basicpayment.repository.paymentDetails.PaymentDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    public PaymentDetails create(PaymentDetailsDTO dto) {
        PaymentDetails entity = PaymentDetails.create(dto);
        return paymentDetailsRepository.save(entity);
    }

}
