package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;



@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	
	public void makeAccount(Account acc) throws Exception{
		acc.setBalance(0); //초기화
		accountRepository.save(acc);
	}
	
	public Account accountinfo(String id) throws Exception {
		Optional<Account> oacc = accountRepository.findById(id);
		if(!oacc.isPresent()) throw new Exception("계좌번호 오류");
		return oacc.get();
	}
	
	public Boolean checkDoubleId(String id) throws Exception{
		//계좌번호가 존재할 때 : true, 존재하지 않을 때 : false
		Optional<Account> oacc = accountRepository.findById(id);
		if(oacc.isPresent()) return true; //계좌번호 이미 존재
		return false;
	}
	
	public Integer deposite(String id, Integer money) throws Exception{
		//계좌번호가 존재할 때 : true, 존재하지 않을 때 : false
		Optional<Account> oacc = accountRepository.findById(id);
		if(!oacc.isPresent()) throw new Exception("계좌번호 오류");
		Account acc = oacc.get();
		acc.deposite(money);
		accountRepository.save(acc);
		return acc.getBalance();
	}
	
	public Integer withdraw(String id, Integer money) throws Exception{
		//계좌번호가 존재할 때 : true, 존재하지 않을 때 : false
		Optional<Account> oacc = accountRepository.findById(id);
		if(!oacc.isPresent()) throw new Exception("계좌번호 오류");
		Account acc = oacc.get();
		acc.withdraw(money);
		accountRepository.save(acc);
		return acc.getBalance();
	}
	
	public Account transfer(String id, Integer money, String send) throws Exception{
		Optional<Account> osacc = accountRepository.findById(send);
		if(!osacc.isPresent()) throw new Exception("보내는 계좌번호 오류");
		Account sacc = osacc.get();
		sacc.withdraw(money);

		
		Optional<Account> oracc = accountRepository.findById(id);
		if(!oracc.isPresent()) throw new Exception("받는 계좌번호 오류");
		Account racc = oracc.get();
		racc.deposite(money);
		
		accountRepository.save(sacc);
		accountRepository.save(racc);
		return sacc;
	}
	
	public List<Account> allAccount() {
		return accountRepository.findAll();
	}
}
