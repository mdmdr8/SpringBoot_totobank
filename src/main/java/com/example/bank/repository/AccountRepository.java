package com.example.bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
	
}
//entity, jpa = orm(쿼리의 결과를 가지고 객체로 매핑해서 온다.)이야.jdbc (스프링jdbc 자바jdbc )는 변수 하나하나 가져와서 객체를 만든다

