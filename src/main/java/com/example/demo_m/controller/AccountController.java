package com.example.demo_m.controller;

import com.example.demo_m.entities.Account;
import com.example.demo_m.entities.Event;
import com.example.demo_m.exception.HandleException;
import com.example.demo_m.service.AccountService;
import com.example.demo_m.service.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    EventService eventService;
    @GetMapping("list")
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @PostMapping("create")
    public ResponseEntity<Account> create(@RequestBody Account account){
        return new ResponseEntity<Account> (accountService.save(account), HttpStatus.OK);
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
    public ResponseEntity<?> findById(@RequestParam("id")Long id){
        Optional<Account> accountOptional= accountService.findById(id);
        if(accountOptional.isPresent()){


            return ResponseEntity.ok(accountService.save(accountOptional.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HandleException(404,"not found id: "+id));


        }


    }
    @PostMapping("addEventInAccount")
    public ResponseEntity<Account> addEventInAccount(@RequestParam("idAccount") Long idAccount,@RequestBody Event event){
        Optional<Account> accountOptional= accountService.findById(idAccount);
        if(accountOptional.isPresent()){
            eventService.save(event);
            List<Event> events= accountOptional.get().getEvents();
            events.add(event);
            accountOptional.get().setEvents(events);
            return ResponseEntity.ok().body(accountService.save(accountOptional.get()));
        }
        return  ResponseEntity.notFound().build();



    }


}
