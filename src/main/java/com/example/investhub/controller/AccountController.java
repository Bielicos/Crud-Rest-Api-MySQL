package com.example.investhub.controller;

import com.example.investhub.dto.AccountResponseDto;
import com.example.investhub.dto.AccountStockResponseDto;
import com.example.investhub.dto.AssociateAccountStockDto;
import com.example.investhub.dto.CreateAccountDto;
import com.example.investhub.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/users")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<String> createUserAccount(@PathVariable String userId, @RequestBody CreateAccountDto createAccountDTO) {
        accountService.createAccount(userId, createAccountDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> getUserAccounts(@PathVariable("userId") String userId) {
        var accounts = accountService.getAllAccountsFromUser(userId);
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock (@PathVariable("accountId") String accountId,
                                                @RequestBody AssociateAccountStockDto associateAccountStockDto) {
        accountService.associateStock(accountId, associateAccountStockDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> listStocksFromAccount (@PathVariable("accountId") String accountId) {
        var stocks = accountService.listStocks(accountId);
        return ResponseEntity.ok(stocks);
    }
}
