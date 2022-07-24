package com.evam.Service;

import com.evam.dtos.BillDTO;

import java.util.List;

public interface BillService extends HelperService<BillDTO> {
    List<BillDTO> getBillList();

}
