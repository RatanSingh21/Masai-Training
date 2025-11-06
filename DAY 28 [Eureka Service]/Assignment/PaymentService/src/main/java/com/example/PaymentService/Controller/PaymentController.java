package com.example.PaymentService.Controller;

import com.example.PaymentService.Entity.Payment;
import com.example.PaymentService.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    PaymentService service;

    @PostMapping("/payments")
    public ResponseEntity<Payment> placePayment(@RequestBody Payment payment)
    {
          return service.placePayment(payment);
    }

    @GetMapping("payments/{id}")
    public ResponseEntity<Payment> getById(@PathVariable Long id)
    {
        return service.getById(id);
    }
}
