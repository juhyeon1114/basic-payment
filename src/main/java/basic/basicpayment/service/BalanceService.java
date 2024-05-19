package basic.basicpayment.service;

import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.balance.Balance;
import basic.basicpayment.model.balance.BalanceDTO;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.repository.appUser.AppUserRepository;
import basic.basicpayment.repository.balance.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final AppUserRepository appUserRepository;

    public Balance createOrUpdateBalance(Long userId, Float newBalance, BalanceCurrency currency) {
        Balance balance = balanceRepository.findOneByUserIdAndCurrency(userId, currency);
        if (balance == null) { // balance 생성
            AppUser appUser = appUserRepository.findOneOrThrow(userId);
            BalanceDTO dto = BalanceDTO.create(newBalance, currency, appUser);
            return balanceRepository.save(Balance.create(dto));
        } else { // Balance 업데이트
            balance.updateBalance(newBalance);
            return balance;
        }
    }

}
