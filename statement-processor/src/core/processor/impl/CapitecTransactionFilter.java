package core.processor.impl;

import com.spire.pdf.PdfDocument;
import core.processor.in.TransactionFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapitecTransactionFilter implements TransactionFilter {


    @Override
    public List<String> filterTransactions(List<String> transactions) {
        List<String> filteredTransactions = new ArrayList<>();
        for (String transaction : transactions) {
            Pattern pattern = Pattern.compile("([0-9]{2}/[0-9]{2}/[0-9]{4}) ([0-9]{2}/[0-9]{2}/[0-9]{4}) (.+)");
            Matcher matcher = pattern.matcher(transaction);

            if (matcher.matches()) {
                System.out.println("Transaction: " + transaction);
                String postingDate = matcher.group(1);
                String transactionDate = matcher.group(2);
                String description = matcher.group(3);
//                String moneyIn = matcher.group(4);
//                String moneyOut = matcher.group(5);
//                String balance = matcher.group(6);

                // Filter based on conditions
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    @Override
    public HashMap<String, String> getTransactionAmounts(String transaction) {
        String regex ="(([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?( ([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[Ee]([+-]?\\d+))?)+)";
        Pattern pattern = Pattern.compile(regex);
        HashMap<String, String> transactionAmounts = new HashMap<>();
            Matcher matcher = pattern.matcher(transaction);
            if (matcher.matches()) {
                String amount = matcher.group(1);
                transactionAmounts.put(transaction, amount);
            }

        return transactionAmounts;
    }


    @Override
    public List<String> getTransactionsFromStatement(PdfDocument statement) {
        return null;
    }
}
