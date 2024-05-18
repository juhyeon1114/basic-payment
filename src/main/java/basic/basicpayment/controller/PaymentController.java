package basic.basicpayment.controller;

import basic.basicpayment.model.dto.UserBalanceResponse;
import basic.basicpayment.service.BalanceReadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Tag(name = "Payment")
public class PaymentController {

    private final BalanceReadService balanceReadService;

    @GetMapping("/balance/{userId}")
    public List<UserBalanceResponse> getUserBalance(@PathVariable("userId") Long userId) {
        return balanceReadService.getUserBalance(userId);
    }


}
