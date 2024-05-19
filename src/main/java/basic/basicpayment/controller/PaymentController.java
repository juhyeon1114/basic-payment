package basic.basicpayment.controller;

import basic.basicpayment.model.dto.*;
import basic.basicpayment.model.payment.PaymentApprovalRequest;
import basic.basicpayment.service.BalanceReadService;
import basic.basicpayment.service.PaymentReadService;
import basic.basicpayment.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Tag(name = "Payment")
public class PaymentController {

    private final BalanceReadService balanceReadService;
    private final PaymentReadService paymentReadService;
    private final PaymentService paymentService;

    @GetMapping("/balance/{userId}")
    public List<UserBalanceResponse> getUserBalance(@PathVariable("userId") Long userId) {
        return balanceReadService.getUserBalance(userId);
    }

    @PostMapping("/estimate")
    public EstimateResponse estimatePayment(@RequestBody @Valid EstimateRequest req) {
        return paymentReadService.estimate(req);
    }

    @PostMapping("/approval")
    public PaymentApprovalResponse paymentApproval(@RequestBody @Valid PaymentApprovalRequest req) {
        return paymentService.paymentApproval(req);
    }


}
