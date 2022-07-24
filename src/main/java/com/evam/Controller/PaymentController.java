package com.evam.Controller;


import com.evam.dtos.PaymentDTO;
import com.evam.Service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Data
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("payment")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "list")
    public ResponseEntity<List<PaymentDTO>> getPaymentList() {
        List<PaymentDTO> payments = paymentService.getPaymentList();
        log.info("All payments Returned - {}", payments);
        return ResponseEntity.ok(payments);
    }

    @PostMapping(value = "save")
    public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO payment) throws ParseException {
        PaymentDTO payment1 = paymentService.Create(payment);
        return ResponseEntity.ok(payment1);
    }


    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody PaymentDTO payment) {
        String status = paymentService.Update(id, payment);
        log.info("Payment Updated Status - {}", status);
        return ResponseEntity.ok("Payment updated!");
    }

    @DeleteMapping(value = "remove/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable() Long id) {
        String status = paymentService.Delete(id);
        log.info("Payment Deleted Status - {}", status);
        return ResponseEntity.ok(id + "th Customer deleted!");
    }
}






    