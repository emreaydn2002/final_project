package com.evam.Controller;

import com.evam.Service.BillService;
import com.evam.dtos.BillDTO;
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
@RequestMapping("bill")
@Slf4j
@RequiredArgsConstructor
public class BillController {

    public BillController(BillService billService) {
        this.billService = billService;
    }
    @Autowired
    private BillService billService;

    @GetMapping(value = "list")
    public ResponseEntity<List<BillDTO>> getBillList() {
        List<BillDTO> billList = billService.getBillList();
        log.info("All Activities Returned - {}",billList);
        return ResponseEntity.ok(billList);
    }

    @PostMapping(value = "save")
    public ResponseEntity<BillDTO> saveBill(@RequestBody BillDTO billDTO) throws ParseException {
        BillDTO bill1 = billService.Create(billDTO);
        return ResponseEntity.ok(bill1);
    }



    @PutMapping(value = "update/{id}")
    public ResponseEntity<String> updateBill(@PathVariable Long id, @RequestBody BillDTO billDTO) {
        String status = billService.Update(id,billDTO);
        log.info("bill Updated Status - {}",status);
        return ResponseEntity.ok("Bill updated!");
    }

    @DeleteMapping(value = "remove/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable() Long id) {
        String status = billService.Delete(id);
        log.info("Bill Deleted Status - {}",status);
        return ResponseEntity.ok(id + "th Bill deleted!");
    }
}

