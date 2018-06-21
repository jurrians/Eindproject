package e.gebruiker.eindproject;

import java.util.Date;

public class Income {

    public float totalIncome;
    public Date payDay;

    public String mainIncomeName;
    public float mainIncomeAmount;

    private String mainIncomeDebitor;
    private String mainINcomeDebitorAccount;


    public class income {
        String incomeName;
        float incomeAmount;
        String incomeDebitor;
        String incomeDebitorAccount;
        Date incomeDate;
    }

    // select transaction from transActionList to add to income
    public void transactionSelecter() {

    }

    // check if main income is transferred to account
    // --> used to determine new cycle
    public void transactionChecker() {

    }

    // change which transaction is regarded as main income
    public void changeMainIncome() {

    }

    // add income data to SQL-db
    private void sqlInjecter() {

    }

    // get income data from SQL-db
    public void sqlGetter() {

    }

    // remove individual income from SQL-db
    private void incomeRemover() {

    }

    // calculate users totalIncome
    private void incomeCalculator() {

    }

}
