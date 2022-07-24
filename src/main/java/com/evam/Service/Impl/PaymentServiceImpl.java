package com.evam.Service.Impl;



import com.evam.Model.Payment;
import com.evam.Repository.PaymentRepository;
import com.evam.Service.PaymentService;
import com.evam.dtos.PaymentDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PaymentDTO> getPaymentList(){
        List<Payment> branchList = (List<Payment>) paymentRepository.findAll();
        return branchList.stream().map(payment -> modelMapper.map(payment, PaymentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO Create(PaymentDTO model) throws ParseException {
        Payment payment = modelMapper.map(model,Payment.class);
        Optional<Payment> payment1 = paymentRepository.findById(model.getId());
        if(payment1.isEmpty())
            return modelMapper.map(paymentRepository.save(payment),PaymentDTO.class);
        throw new IllegalArgumentException(model + " Create Option Fail!");
    }
    @Override
    public String Update(Long id, PaymentDTO model) {
        Payment payment = modelMapper.map(model,Payment.class);
        Optional<Payment> payment1 = paymentRepository.findById(id);
        if(payment1.isPresent()){
            if(id.equals(model.getId())) {
                paymentRepository.save(payment);
                return "ID:" + payment.getId() + " Updated!";}}
        throw new IllegalArgumentException(model + " Update Option Fail!");
    }

    @Override
    public String Delete(Long id) {
        Optional<Payment> _payment = paymentRepository.findById(id);
        if (_payment.isPresent()) {
            paymentRepository.deleteById(id);
            return id.toString() + "Deleted";
        } else
            throw new IllegalArgumentException(" Delete Option Fail!");
    }
}


