package com.example.investhub.service;

import com.example.investhub.dto.AccountResponseDto;
import com.example.investhub.dto.AssociateAccountStockDto;
import com.example.investhub.dto.CreateAccountDto;
import com.example.investhub.entity.Account;
import com.example.investhub.entity.AccountStock;
import com.example.investhub.entity.AccountStockId;
import com.example.investhub.entity.BillingAddress;
import com.example.investhub.repository.AccountRepository;
import com.example.investhub.repository.AccountStockRepository;
import com.example.investhub.repository.StockRepository;
import com.example.investhub.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private StockRepository stockRepository;
    private UserRepository userRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(AccountRepository accountRepository, StockRepository stockRepository,
                          UserRepository userRepository,  AccountStockRepository accountStockRepository) {
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
        this.accountStockRepository = accountStockRepository;
    }

    public void createAccount (String userId, CreateAccountDto createAccountDTO) {
        Integer id = Integer.parseInt(userId);
        var userEntity = userRepository.findById(id).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                }
        );

        // DTO -> Entity
        var account = new Account();
        account.setUser(userEntity);
        account.setDescription(createAccountDTO.description());

        var billingAddress = new BillingAddress();
        billingAddress.setStreet(createAccountDTO.street());
        billingAddress.setNumber(createAccountDTO.number());

        // Relacionamento
        billingAddress.setAccount(account);
        account.setBillingAddress(billingAddress); // BillingAdress vai ser salvo ao entrar no cascade.

        var accountCreated = accountRepository.save(account);
    }

    public List<AccountResponseDto> getAllAccountsFromUser (String userId) {
        // Checando se o usuário existe
        Integer id = Integer.parseInt(userId);
        var userEntity = userRepository.findById(id).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                }
        );

        // Pega a lista de usuarios e mapeia para transforma-la em uma lista de AccountResponseDto
        return userEntity.getAccounts()
                .stream()
                .map(ac -> // Cada um dos usuários está se tornando uma instância de AccountResponseDto
                        new AccountResponseDto(Integer.toString(ac.getAccountId()) , ac.getDescription()))
                .toList(); // Todas as instâncias são colocadas na lista.
    }


    public void associateStock(String accountId, AssociateAccountStockDto dto) {
        Integer IntegerAccountId = Integer.parseInt(accountId);
        // Checar se a conta existe
        var accountEntity = accountRepository.findById(IntegerAccountId).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
                }
        );

        // Checar se a ação existe.
        var stockEntity = stockRepository.findById(dto.stockId()).orElseThrow(
                () -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found");
                }
        );

        // DTO -> Entity
        var id = new AccountStockId();
        id.setAccountId(accountEntity.getAccountId());
        id.setStockId(stockEntity.getStockId());

        var entity = new AccountStock();
        entity.setId(id);
        entity.setAccount(accountEntity);
        entity.setStock(stockEntity);
        entity.setQuantity(dto.quantity());

        accountStockRepository.save(entity);
    }

    public Object listStocks(String accountId) {
        
    }
}
