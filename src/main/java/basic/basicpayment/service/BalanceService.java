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

    /**
     * Balance를 생성하거나 업데이트 합니다.
     * 예를 들어서, 유저 A가 KRW에 해당하는 Balance가 없다면, 새롭게 데이터를 생성한 후에 반환합니다.
     * 반대로, 유저 A가 KRW에 해당하는 Balance가 이미 있다면, 해당 데이터를 업데이트한 후에 반환합니다.
     * 
     * @param userId - 유저 ID
     * @param newBalance - 새롭게 추가할 Balance 값
     * @param currency - 화폐 단위
     * @return Balance - 유저의 잔액 데이터
     */
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
