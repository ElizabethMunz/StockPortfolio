package edu.temple.munz.stockportfolio;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchDialogFragment extends DialogFragment {

    Context context;
    EditText editTextInput;

    public SearchDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
       //get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_search_dialog,null);
        builder.setView(dialogView);
        editTextInput = (EditText)dialogView.findViewById(R.id.inputSymbol);
        builder.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //when the Go button is clicked
                        //get input text
                        String symbol = editTextInput.getText().toString().trim().toUpperCase();
                        findStockInfo(symbol);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //when the Cancel button is clicked, the dialog just closes
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }


    public void findStockInfo(final String s) {

        Thread t = new Thread() {
            @Override
            public void run() {
                //get the stock info from URL using its symbol
                try {
                    URL stockInfoURL = new URL("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json/?symbol=" + s);
                    Log.d("URL", stockInfoURL.toString()); //for testing
                    //read data from the URL into a JSONObject
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(stockInfoURL.openStream()));

                    String response = "";

                    String tmpResponse = reader.readLine();
                    while (tmpResponse != null) {
                        response = response + tmpResponse;
                        tmpResponse = reader.readLine();
                    }

                    JSONObject stockObject = new JSONObject(response);
                    Message message = Message.obtain();
                    message.obj = stockObject;

                    findStockInfoHandler.handleMessage(message);
                    //test if we successfully got the data by logging LastPrice
                    //Log.d("Price", Double.toString(stockObject.getDouble("LastPrice")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }


    Handler findStockInfoHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            JSONObject stockObject = (JSONObject)message.obj;

            Stock newStock = new Stock(stockObject);

            saveStock(newStock);


            return false;
        }
    });

    public void saveStock(Stock stock) {

    }


    interface StockInterface {
        public Stock[] findStockInfo();

    }

}
