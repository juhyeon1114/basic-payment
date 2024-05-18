package basic.basicpayment.repository.appUser;

import basic.basicpayment.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, CustomAppUserRepository {

    @Override
    @Query(
            value = "select * from app_user AU where AU.id = :id limit 1",
            nativeQuery = true
    )
    AppUser findOneOrNull(@Param("id") Long id);
}
