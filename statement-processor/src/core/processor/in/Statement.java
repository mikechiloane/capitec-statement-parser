package core.processor.in;

import java.util.List;

/**
 * The Statement interface represents a statement that can be loaded from a file, saved as an Excel file,
 * and provides access to the transactions in the statement.
 */
public interface Statement {

    /**
     * Loads the statement from the specified file path.
     *
     * @param path the path of the file to load the statement from
     */
    void loadStatementFromFile(String path);

    /**
     * Saves the statement as an Excel file.
     */
    void saveStatementAsExcel(String statementName);

    /**
     * Retrieves the transactions in the statement.
     *
     * @return a list of strings representing the transactions in the statement
     */
    List<String> getStatementTransactions();

}
