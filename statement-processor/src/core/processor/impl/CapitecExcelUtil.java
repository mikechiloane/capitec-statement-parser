package core.processor.impl;

import core.processor.in.ExcelUtility;
import main.capitec.entity.Transaction;
import main.capitec.entity.TransactionRows;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utility class for parsing Capitec bank statements from an Excel file.
 */
public class CapitecExcelUtil implements ExcelUtility {

    private final XSSFWorkbook workbook;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private final TransactionRows transactionRowsForFirstPage = new TransactionRows(4, 7, 16, 18, 19, 26);
    private final TransactionRows transactionRowsForOtherPages = new TransactionRows(3, 6, 15, 18, 19, 1);
    private List<String> transactions;

    /**
     * Constructs a new instance of CapitecExcelUtil with the specified file path.
     *
     * @param path the path to the Excel file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public CapitecExcelUtil(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(file);
    }

    // Rest of the code...
}
public class CapitecExcelUtil implements ExcelUtility {

    private final XSSFWorkbook workbook;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private final TransactionRows transactionRowsForFirstPage = new TransactionRows(4, 7, 16, 18, 19, 26);
    private final TransactionRows transactionRowsForOtherPages = new TransactionRows(3, 6, 15, 18, 19, 1);
    private List<String> transactions;

    public CapitecExcelUtil(String path) throws IOException {
        FileInputStream file = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(file);
    }

    private static double parseMoneyValue(String cellValue) {
        try {
            if (cellValue == null || cellValue.isBlank()) {
                return 0;
            }
            boolean charInValue = cellValue.chars().anyMatch(Character::isLetter);
            if (charInValue) {
                int indexOfChar = cellValue.indexOf(".");
                return Double.parseDouble(cellValue.substring(0, indexOfChar).replaceAll("\\s", ""));
            }
            return Double.parseDouble(cellValue.replaceAll("\\s", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<Transaction> getExcelTransactions() {
        int numberOfSheets = workbook.getNumberOfSheets();
        List<Transaction> transactions = new ArrayList<>();
        if (numberOfSheets > 0) {
            transactions.addAll(v2(transactionRowsForFirstPage, 1));
            for (int i = 1; i < numberOfSheets; i++) {
                transactions.addAll(v2(transactionRowsForOtherPages, i));
            }
        }
        return transactions;
    }

    public List<Transaction> v2(TransactionRows transactionRows, int index) {
        try {
            List<Transaction> transactions = new ArrayList<>();
            int row = transactionRows.start();

            while (!endOfTransactions(index, row, transactionRows)) {

                String dateString = getCellValue(index, row, transactionRows.date());

                Date date = dateFormat.parse(dateString);
                String descriptionCellValue = getCellValue(index, row, transactionRows.description());
                String moneyInCellValue = getCellValue(index, row, transactionRows.moneyIn());
                String moneyOutCellValue = getCellValue(index, row, transactionRows.moneyOut());
                String balanceCellValue = getCellValue(index, row, transactionRows.balance());
                System.out.println("Date: " + date + " Description: " + descriptionCellValue + " Money In: " + moneyInCellValue + " Money Out: " + moneyOutCellValue + " Balance: " + balanceCellValue);
                transactions.add(new Transaction(date, descriptionCellValue, parseMoneyValue(moneyInCellValue), parseMoneyValue(moneyOutCellValue), parseMoneyValue(balanceCellValue)));

                if (nextTransactionEmpty(index, row, transactionRows)) {
                    row += 2;
                } else {
                    row++;
                }

            }

            return transactions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String getCellValue(int index, int row, int cell) {
        String value = workbook.getSheetAt(index).getRow(row + 1).getCell(cell).getStringCellValue();
        if (value.isBlank()) return null;
        return value;
    }

    private boolean endOfTransactions(int index, int row, TransactionRows transactionRows) {
        boolean currTransaction = nextTransactionEmpty(index, row - 1, transactionRows);
        boolean nextTransaction = nextTransactionEmpty(index, row, transactionRows);
        return currTransaction && nextTransaction;
    }

    private boolean nextTransactionEmpty(int index, int row, TransactionRows transactionRows) {
        return getCellValue(index, row + 1, transactionRows.date()) == null;
    }
}


