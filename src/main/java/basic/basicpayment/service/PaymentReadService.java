package basic.basicpayment.service;

import basic.basicpayment.model.dto.EstimateRequest;
import basic.basicpayment.model.dto.EstimateResponse;
import basic.basicpayment.repository.appUser.AppUserRepository;
import basic.basicpayment.repository.merchant.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentReadService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;

    public EstimateResponse estimate(EstimateRequest req) {
        appUserRepository.findOneOrThrow(req.getUserId());
        merchantRepository.findOneOrThrow(req.getMerchantId());

        return EstimateResponse.create(req.getAmount(), req.getCurrency());
    }

}
