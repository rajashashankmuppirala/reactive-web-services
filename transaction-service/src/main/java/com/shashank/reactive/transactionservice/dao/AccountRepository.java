package com.shashank.reactive.transactionservice.dao;

import com.shashank.reactive.transactionservice.model.AccountHolder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AccountRepository extends ReactiveCrudRepository<AccountHolder, Long> {


}
