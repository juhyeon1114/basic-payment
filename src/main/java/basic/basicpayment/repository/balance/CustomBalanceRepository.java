package basic.basicpayment.repository.balance;

import basic.basicpayment.model.balance.Balance;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.repository.common.CustomRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomBalanceRepository extends CustomRepository<Balance, Long> {

    @Override
    @Query(
            value = "select * from balance B where B.id = :id limit 1",
            nativeQuery = true
    )
    Balance findOneOrNull(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select B from Balance B where B.appUser.id=:userId and B.currency=:currency")
    Balance findOneByUserIdAndCurrency(@Param("userId") Long userId, @Param("currency") BalanceCurrency currency);

}
