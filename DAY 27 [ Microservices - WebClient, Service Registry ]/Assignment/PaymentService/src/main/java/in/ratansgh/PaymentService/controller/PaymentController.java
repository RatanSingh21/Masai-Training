package in.ratansgh.PaymentService.controller;

import in.ratansgh.PaymentService.dto.PaymentDTO;
import in.ratansgh.PaymentService.entity.Payment;
import in.ratansgh.PaymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO dto) {
        Payment payment = service.createPayment(dto);
        return new ResponseEntity<>(new PaymentDTO(payment.getOrderId(), payment.getStatus()), HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentDTO> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(service.getPaymentByOrderId(orderId));
    }
}

