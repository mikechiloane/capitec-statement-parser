package core.processor.in;

import com.spire.pdf.PdfDocument;

import java.util.HashMap;
import java.util.List;

public interface TransactionFilter {

    List<String> filterTransactions(List<String> transactions);
    HashMap<String,String> getTransactionAmounts(String transactions);
    List<String> getTransactionsFromStatement(PdfDocument statement);


}
