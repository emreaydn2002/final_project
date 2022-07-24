package com.evam.Service.Impl;


import com.evam.Model.Customer;
import com.evam.Repository.CustomerRepository;
import com.evam.Service.BillService;
import com.evam.Service.CustomerService;
import com.evam.Service.PaymentService;
import com.evam.dtos.BillDTO;
import com.evam.dtos.CustomerDTO;
import com.evam.dtos.PaymentDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final BillService billService;
    private final PaymentService paymentService;

    @Override
    public List<CustomerDTO> getCustomerList() {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        return customerList.stream().map(branch -> modelMapper.map(branch, CustomerDTO.class)).collect(Collectors.toList());
    }
//
//    @Override
//    public CustomerDTO Create(CustomerDTO model) throws ParseException {
//        Customer customer = modelMapper.map(model,Customer.class);
//        Optional<Customer> customer1 = customerRepository.findById(model.getId());
//        if(customer1.isEmpty())
//            return modelMapper.map(customerRepository.save(customer),CustomerDTO.class);
//        throw new IllegalArgumentException(model + " Create Option Fail!");
//    }

    @Override
    public CustomerDTO Create(CustomerDTO model) throws ParseException {
        Customer customer = modelMapper.map(model, Customer.class);
        Optional<Customer> customer1 = customerRepository.findById(model.getId());
        if (customer1.isEmpty())
            return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
        throw new IllegalArgumentException(model + " Create Option Fail!");
    }

    @Override
    public String Update(Long id, CustomerDTO model) {
        Customer customer = modelMapper.map(model, Customer.class);
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {
            if (id.equals(model.getId())) {
                customerRepository.save(customer);
                return "ID:" + customer.getId() + " Updated!";
            }
        }
        throw new IllegalArgumentException(model + " Update Option Fail!");

    }

    @Override
    public String Delete(Long id) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        List<PaymentDTO> paymentList = paymentService.getPaymentList();
        List<BillDTO> billList = billService.getBillList();
        if (billList.size() != 0 && paymentList.size() != 0) {
            for (PaymentDTO payment : paymentList) {
                Boolean paymentProcess = billList.contains(payment);
                if (!paymentProcess) {
                    throw new IllegalArgumentException("Since the user have a not paid bill, you can not delete the customer!");
                }
            }
        }

        if (customer1.isPresent()) {
            customerRepository.deleteById(id);
            return id.toString();
        } else
            throw new IllegalArgumentException(" Delete Option Fail!");
    }

}


    