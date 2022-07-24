package com.evam.Service;

import com.evam.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService  extends HelperService<CustomerDTO>{
    List<CustomerDTO> getCustomerList();
}
