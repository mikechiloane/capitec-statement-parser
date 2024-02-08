package core.processor.in;

import main.capitec.entity.Transaction;

import java.util.List;

/**
 * This interface represents an Excel utility that provides methods for retrieving transactions from an Excel file.
 */
public interface ExcelUtility {
        /**
         * Retrieves a list of transactions from an Excel file.
         *
         * @return the list of transactions
         */
        List<Transaction> getExcelTransactions();
}
