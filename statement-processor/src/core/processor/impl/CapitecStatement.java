package core.processor.impl;

import com.spire.pdf.PdfDocument;
import core.processor.in.Statement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@AllArgsConstructor
@Builder
@Setter
public class CapitecStatement implements Statement {

    private PdfDocument statementPDF;
    private final static String STATEMENT_PATH = "excelFiles/";



    public void loadStatementFromFile(String path) {
        try {
            statementPDF = new PdfDocument();
            statementPDF.loadFromFile(path);
        } catch (Exception e) {
            System.out.println("Error loading statement from file: " + e.getMessage());
        }
    }

    public void loadStatmentFromBytes(byte[] pdfDocumentBytes){
        try{
            statementPDF = new PdfDocument();
            statementPDF.loadFromBytes(pdfDocumentBytes);
        }
        catch (Exception e){
            System.out.println("failed to load statement byte");
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


    public void saveStatementAsExcel(String statementName) {
        try {
            assert statementPDF != null;
            statementPDF.saveToFile(STATEMENT_PATH+statementName+".xlsx", com.spire.pdf.FileFormat.XLSX);
        } catch (Exception e) {
            System.out.println("Error saving statement as excel: " + e.getMessage());
        }
    }

    public byte[] readFileToBytes(String statementName) throws IOException {
        Path path = Paths.get(STATEMENT_PATH+statementName+".xlsx");
        return Files.readAllBytes(path);
    }

    public void closeStatement(){
        statementPDF.close();
    }

    public void deleteStatement(String statementName) throws IOException {
        Path path = Paths.get(STATEMENT_PATH+statementName+".xlsx");
        Files.delete(path);
    }


}