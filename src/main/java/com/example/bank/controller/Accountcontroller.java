package com.example.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;

//실제 뷰를 주지 않고 데이터를 내려보내줌(ResponseEntity를 통해)
// 일반 컨트롤러의 리턴타입이 
//레슽컨트롤러에서는 리스펀스 엔터티를 쓰는 것이 보통
@RestController
public class Accountcontroller {
	@Autowired
	AccountService accountService;
	
	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(Account acc){
		ResponseEntity<String> res = null;
		System.out.println(acc);
//		예외처리
		try {
			accountService.makeAccount(acc);
			res = new ResponseEntity<String>("success",  HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res=new ResponseEntity<String>("error",  HttpStatus.BAD_REQUEST);
			
		}
		
		return res;
	}
	
	@PostMapping("/accountinfo")
	public ResponseEntity<Account> accountinfo(@RequestParam("id") String id){
		ResponseEntity<Account> res =null;
		System.out.println("id:" + id);
		
		try {
			Account acc = accountService.accountinfo(id);
			res = new ResponseEntity<Account>(acc, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			res = new ResponseEntity<Account>(new Account(), HttpStatus.BAD_REQUEST);
		}
		return res;
	}

	@PostMapping("/doubleid")
	public ResponseEntity<Boolean> doubleId(@RequestParam("id") String id){
		ResponseEntity<Boolean> res = null;
		System.out.println(id);
		try {
			Boolean isdouble = accountService.checkDoubleId(id);
			res =new ResponseEntity<Boolean>(isdouble, HttpStatus.OK);
		} catch(Exception e){
			res =new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@PostMapping("/deposite")
	public ResponseEntity<Integer> deposite(@RequestParam("id") String id
			,@RequestParam("money") Integer money){
		ResponseEntity<Integer> res = null;
		System.out.println(id);
		try {
			Integer balance = accountService.deposite(id, money);
			System.out.println(id);
			res =new ResponseEntity<Integer>(balance, HttpStatus.OK);
		} catch(Exception e){
			res =new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Integer> withdraw(@RequestParam("id") String id
			,@RequestParam("money") Integer money){
		ResponseEntity<Integer> res = null;
		System.out.println(id);
		try {
			Integer balance = accountService.withdraw(id, money);
			res =new ResponseEntity<Integer>(balance, HttpStatus.OK);
		} catch(Exception e){
			res =new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<Account> transfer(@RequestParam("id") String id, @RequestParam("money") Integer money, 
			@RequestParam("send") String send){
		ResponseEntity<Account> res = null;

		try {
			Account balance = accountService.transfer(id, money, send);
			res =new ResponseEntity<Account>(balance, HttpStatus.OK);
		} catch(Exception e){
			res =new ResponseEntity<Account>(new Account(), HttpStatus.BAD_REQUEST);
		}
		return res;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Account>> allAccount(){
		ResponseEntity<List<Account>> res = null;
		List<Account> accs = null;
		try {
			accs= accountService.allAccount();
			res= new ResponseEntity<List<Account>> (accs, HttpStatus.OK);

		} catch(Exception e){
			e.printStackTrace();
			res =new ResponseEntity<List<Account>>(accs, HttpStatus.BAD_REQUEST);
		}
		return res;
	}
}
