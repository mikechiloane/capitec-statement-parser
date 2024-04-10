package main.service;

import main.capitec.entity.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BankStatementUploadService {
    List<Transaction> getTransactionsFromStatementUpload(MultipartFile pdfStatement);
}
