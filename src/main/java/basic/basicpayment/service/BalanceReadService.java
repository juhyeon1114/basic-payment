package basic.basicpayment.service;

import basic.basicpayment.model.AppUser;
import basic.basicpayment.model.Balance;
import basic.basicpayment.model.dto.UserBalanceResponse;
import basic.basicpayment.repository.balance.BalanceRepository;
import basic.basicpayment.repository.appUser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BalanceReadService {

    private final AppUserRepository appUserRepository;
    private final BalanceRepository balanceRepository;

    public List<UserBalanceResponse> getUserBalance(Long userId) {
        AppUser appUser = appUserRepository.findOneOrThrow(userId);
        List<Balance> balances = balanceRepository.getAllByUserId(appUser.getId());

        return balances.stream()
                .map(b -> UserBalanceResponse.createWithEntity(appUser, b))
                .toList();
    }

}
