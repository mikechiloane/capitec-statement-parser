package main.web.controller;


import lombok.RequiredArgsConstructor;
import main.capitec.entity.Transaction;
import main.service.CapitecBankStatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/statement")
@RequiredArgsConstructor
public class StatementController {

    private final CapitecBankStatementService capitecBankStatementService;
    
    @PostMapping("/capitec")
    public ResponseEntity<List<Transaction>> getCapitecStatementTransactions(@RequestParam("pdfStatement") MultipartFile pdfStatement) {
        List<Transaction> transactions = capitecBankStatementService.getTransactionsFromStatementUpload(pdfStatement);
        return ResponseEntity.ok(transactions);
    }

}
