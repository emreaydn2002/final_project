package com.evam.Service.Impl;



import com.evam.Model.Bill;
import com.evam.Service.BillService;
import com.evam.dtos.BillDTO;
import com.evam.Repository.BillRepositrory;
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
public class BillServiceImpl implements BillService {

    private final BillRepositrory billRepositrory;
    private final ModelMapper modelMapper;

    @Override
    public List<BillDTO> getBillList(){
        List<Bill>  billList= (List<Bill>) billRepositrory.findAll();
        return billList.stream().map(bill -> modelMapper.map(bill, BillDTO.class)).collect(Collectors.toList());
    }

    @Override
    public BillDTO Create(BillDTO model) throws ParseException {
        Bill bill = modelMapper.map(model,Bill.class);
        Optional<Bill> bill1 = billRepositrory.findById(model.getId());
        if(bill1.isEmpty())
            return modelMapper.map(billRepositrory.save(bill),BillDTO.class);
        throw new IllegalArgumentException(model + " Create Option Fail!");
    }

    @Override
    public String Update(Long id, BillDTO model) {
        Bill bill = modelMapper.map(model,Bill.class);
        Optional<Bill> bill1 = billRepositrory.findById(id);
        if(bill1.isPresent()){
            if(id.equals(model.getId())) {
                billRepositrory.save(bill);
                return "ID:" + bill.getId() + " Updated!";}}
        throw new IllegalArgumentException(model + " Update Option Fail!");
    }

    @Override
    public String Delete(Long id) {
        Optional<Bill> _bill = billRepositrory.findById(id);
        if (_bill.isPresent()) {
            billRepositrory.deleteById(id);
            return id.toString() + "Deleted";
        } else
            throw new IllegalArgumentException(" Delete Option Fail!");
    }
}

