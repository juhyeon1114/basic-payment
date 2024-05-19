package basic.basicpayment.repository.merchant;

import basic.basicpayment.model.merchant.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, String>, CustomMerchantRepository {
}
