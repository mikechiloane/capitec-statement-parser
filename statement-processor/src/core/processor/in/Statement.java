package core.processor.in;

import java.util.List;

public interface Statement {

    void loadStatementFromFile(String path);
    void saveStatementAsExcel();
    List<String> getStatementTransactions();

}
