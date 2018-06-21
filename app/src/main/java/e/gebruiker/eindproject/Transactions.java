package e.gebruiker.eindproject;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

// ONSAVEINSTANCESTATE --> MAIN

public class Transactions {

    public class Transaction {
        float transactionID;
        Date bookingDate;
        Date executionDateTime;
        float amount;
        String creditorName;
        String creditorAccount;
    }

    public List<Transaction> transactionList;


    // TRANSACTIONREQUEST request transactions as JSON from ING API
    private JsonObjectRequest jsonObjectRequest() {
        return jsonObjectRequest();
    }

    // construct transactionObject from requested data
    private void transactionConstructor() {

    }

    // inject constructed object into transactionList
    private void listInjector() {

    }

    // get all transactions from the transactionList
    public void transactionGetter() {

    }

    // look up transactions in list with specified value
    public void transactionSearcher() {

    }

    private void listViewAdapter() {

    }









}
