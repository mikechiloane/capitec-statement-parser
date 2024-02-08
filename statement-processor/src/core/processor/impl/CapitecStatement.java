package core.processor.impl;

import com.spire.pdf.PdfDocument;
import core.processor.in.Statement;

import java.util.List;


public class CapitecStatement implements Statement {

    private PdfDocument statementPDF;
    private final String statementFilePath;

    public CapitecStatement(String statementFilePath) {
        this.statementFilePath = statementFilePath;
    }

    public void loadStatementFromFile(String path) {
        try {
            statementPDF = new PdfDocument();
            statementPDF.loadFromFile(path);
        } catch (Exception e) {
            System.out.println("Error loading statement from file: " + e.getMessage());
        }
    }

    public List<String> getStatementTransactions() {
        try {
            assert statementPDF != null;
            return null;
        } catch (Exception e) {
            System.out.println("Error getting transactions from statement: " + e.getMessage());
        }

        return null;
    }


    public void saveStatementAsExcel() {
        try {
            assert statementPDF != null;
            statementPDF.saveToFile(statementFilePath);
        } catch (Exception e) {
            System.out.println("Error saving statement as excel: " + e.getMessage());
        }
    }

}