package basic.basicpayment.repository.balance;

import basic.basicpayment.model.balance.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BalanceRepository extends JpaRepository<Balance, Long>, CustomBalanceRepository {

    @Query("select b from Balance b where b.appUser.id = :userId")
    List<Balance> getAllByUserId(@Param("userId") Long userId);

}
