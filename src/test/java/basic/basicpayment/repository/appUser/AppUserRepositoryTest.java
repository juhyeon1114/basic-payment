package basic.basicpayment.repository.appUser;

import basic.basicpayment.exception.exceptions.ItemNotFoundException;
import basic.basicpayment.model.appUser.AppUser;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppUserRepositoryTest {


    @Autowired
    AppUserRepository appUserRepository;

    @Test
    @DisplayName("유저생성 및 조회")
    void 유저생성_및_조회() throws Exception {
        AppUser entity = AppUser.create("test@test.com");
        AppUser saved = appUserRepository.save(entity);

        Long userId = saved.getId();
        assertNotNull(userId, "유저 생성");

        AppUser found = appUserRepository.findOneOrNull(userId);
        assertEquals(found.getId(), userId, "유저 조회");

        assertThrows(ItemNotFoundException.class, () -> {
            appUserRepository.findOneOrThrow(9999L);
        }, "존재하지 않는 유저 조회");
    }
    

}