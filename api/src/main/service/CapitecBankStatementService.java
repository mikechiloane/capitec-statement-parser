package main.service;

import core.processor.impl.CapitecExcelUtil;
import core.processor.impl.CapitecStatement;
import main.capitec.entity.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public class CapitecBankStatementService implements BankStatementUploadService{
    @Override
    public List<Transaction> getTransactionsFromStatementUpload(MultipartFile pdfStatement) {
        CapitecStatement capitecStatement = CapitecStatement.builder().build();
        try {
            String fileName = generateRandomFileName();
            capitecStatement.loadStatmentFromBytes(pdfStatement.getBytes());
            capitecStatement.saveStatementAsExcel(fileName);

            byte[]  excelBytes = capitecStatement.readFileToBytes(fileName);
            capitecStatement.deleteStatement(fileName);

            CapitecExcelUtil capitecExcelUtil = new CapitecExcelUtil(excelBytes);

            return capitecExcelUtil.getExcelTransactions();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private  String generateRandomFileName(){
        return UUID.randomUUID().toString();
    }
}
