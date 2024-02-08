package main.capitec.entity;

import java.util.Date;


public record Transaction(Date Date, String Description, double moneyIn, double moneyOut, double balance) {

}
