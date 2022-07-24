package com.evam.Repository;


import com.evam.Model.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepositrory extends CrudRepository<Bill, Long> {
}
