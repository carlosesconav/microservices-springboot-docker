package com.devsu.accounts.interfaces.contoller;


import com.devsu.accounts.application.dto.AccountDTO;
import com.devsu.accounts.application.dto.ResponseDTO;
import com.devsu.accounts.application.mapper.ResponseMapper;
import com.devsu.accounts.application.service.interfaces.IAccoutnService;
import com.devsu.accounts.domain.enums.ExceptionEnum;
import com.devsu.accounts.domain.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path="/api/accounts")
public class AccountController {

    @Autowired
    private IAccoutnService accountService;

    private final ResponseMapper responseMapper = new ResponseMapper();

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = accountService.createAccount(accountDTO);


            return ResponseEntity.created(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .build()
                    .toUri()).body(responseDTO);
        } catch (ServiceException se) {
            responseDTO = responseMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = responseMapper.responseDTO(e.getMessage(), ExceptionEnum.INTERNAL_SERVER_ERROR.getHttpCode());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }


    @PatchMapping(path = "/add/balance")
    public ResponseEntity<ResponseDTO> addBalance(@RequestBody AccountDTO accountDTO) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = accountService.addBalance(accountDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = responseMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = responseMapper.responseDTO(e.getMessage(), ExceptionEnum.INTERNAL_SERVER_ERROR.getHttpCode());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @PatchMapping(path = "/withdraw/balance")
    public ResponseEntity<ResponseDTO> withdrawBalance(@RequestBody AccountDTO accountDTO) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = accountService.withdrawBalance(accountDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = responseMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = responseMapper.responseDTO(e.getMessage(), ExceptionEnum.INTERNAL_SERVER_ERROR.getHttpCode());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }

    @GetMapping(path = "/report/{accountNumber}")
    public ResponseEntity<ResponseDTO> getReport(@PathVariable String accountNumber, @RequestParam String startDate, @RequestParam String endDate) {
        ResponseDTO responseDTO = null;
        try {
            responseDTO = accountService.getStateAccountReport(accountNumber, startDate, endDate);
            return ResponseEntity.ok(responseDTO);
        } catch (ServiceException se) {
            responseDTO = responseMapper.responseDTO(se.getMessage(), se.getCode());
            return ResponseEntity.status(Integer.parseInt(se.getCode())).body(responseDTO);

        } catch (Exception e) {
            responseDTO = responseMapper.responseDTO(e.getMessage(), ExceptionEnum.INTERNAL_SERVER_ERROR.getHttpCode());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }



}
