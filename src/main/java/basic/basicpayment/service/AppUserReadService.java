package basic.basicpayment.service;

import basic.basicpayment.model.AppUser;
import basic.basicpayment.repository.appUser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppUserReadService {

    private final AppUserRepository appUserRepository;

    public AppUser getOneById(Long id) {
        return appUserRepository.findOneOrThrow(id);
    }

}
