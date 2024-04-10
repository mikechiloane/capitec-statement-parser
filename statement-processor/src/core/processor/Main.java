package core.processor;

import core.processor.impl.CapitecExcelUtil;
import core.processor.impl.CapitecStatement;
import main.capitec.entity.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            byte [] fileBytes= readFileToBytes();
            CapitecStatement capitecStatement = CapitecStatement.builder().build();
            capitecStatement.loadStatementFromFile("/Users/mikechiloane/Downloads/account_statement.pdf");
            capitecStatement.saveStatementAsExcel("pop");
            byte[]  excelBytes = capitecStatement.readFileToBytes("pop");
            capitecStatement.deleteStatement("pop");

            CapitecExcelUtil capitecExcelUtil = new CapitecExcelUtil(excelBytes);
            List<Transaction> transactions = capitecExcelUtil.getExcelTransactions();
            transactions.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static byte[] readFileToBytes() throws IOException {
        Path path = Paths.get("/Users/mikechiloane/Downloads/pop.xlsx");
        return Files.readAllBytes(path);
    }
}
