package basic.basicpayment.repository.merchant;

import basic.basicpayment.model.Merchant;
import basic.basicpayment.repository.common.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomMerchantRepository extends CustomRepository<Merchant, String> {

    @Override
    @Query(
            value = "select * from merchant M where M.id = :id limit 1",
            nativeQuery = true
    )
    Merchant findOneOrNull(@Param("id") String id);

}
