package com.evam.Service;

import com.evam.dtos.PaymentDTO;

import java.util.List;

public interface PaymentService extends HelperService<PaymentDTO>{
    List<PaymentDTO> getPaymentList();
}
