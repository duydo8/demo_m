package com.example.demo_m.controller;

import com.example.demo_m.entities.Account;
import com.example.demo_m.entities.Event;
import com.example.demo_m.repository.AccountRepository;
import com.example.demo_m.service.AccountService;
import com.example.demo_m.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("list")
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<Account> create(@RequestBody Account event){
        return ResponseEntity.ok(accountService.save(event));
    }
    @PutMapping("update")
    public ResponseEntity<Account> update(@RequestBody Account account){
        Optional<Account> accountOptional= accountService.findById(account.getId());
        if(accountOptional.isPresent()){
            BeanUtils.copyProperties(accountOptional,account);

            return ResponseEntity.ok(accountService.save(accountOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("delete")
    public  ResponseEntity<?> delete(@RequestParam("id") Long id){
        accountService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
    @GetMapping("findById")
    public ResponseEntity<Account> findById(@RequestParam("id")Long id){
        Optional<Account> accountOptional= accountService.findById(id);
        if(accountOptional.isPresent()){


            return ResponseEntity.ok(accountService.save(accountOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("addEventInAccount")
    public ResponseEntity<Account> addEventInAccount(@RequestParam("idAccount") Long idAccount,@RequestBody Event event){
        Optional<Account> accountOptional= accountService.findById(idAccount);
        if(accountOptional.isPresent()){
            List<Event> events= accountOptional.get().getEvents();
            events.add(event);
            accountOptional.get().setEvents(events);
            return ResponseEntity.ok().body(accountService.save(accountOptional.get()));
        }
        return  ResponseEntity.notFound().build();



    }


}
