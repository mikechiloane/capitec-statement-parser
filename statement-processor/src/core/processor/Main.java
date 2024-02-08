package core.processor;

import core.processor.impl.CapitecExcelUtil;
import main.capitec.entity.Transaction;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            CapitecExcelUtil capitecExcelUtil = new CapitecExcelUtil("/Users/mikechiloane/Downloads/pop.xlsx");
            List<Transaction> transactions = capitecExcelUtil.getExcelTransactions();
            transactions.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
