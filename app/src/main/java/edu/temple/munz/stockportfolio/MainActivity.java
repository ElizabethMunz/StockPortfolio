package edu.temple.munz.stockportfolio;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addButton;
    PortfolioFragment portfolioFragment;
    DetailFragment detailFragment;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);

        portfolioFragment = new PortfolioFragment();
        detailFragment = new DetailFragment();

        fm = getSupportFragmentManager();

        //put portfolio fragment in the frame
        fm.beginTransaction().replace(R.id.portfolioFrame, portfolioFragment).commit();

        //if detail fragment exists (horizontal orientation or large screen), put it in its frame
        if(findViewById(R.id.detailFrame) != null) {
            fm.beginTransaction().replace(R.id.detailFrame, detailFragment).commit();
        }

        //SearchDialogFragment
        final SearchDialogFragment searchDialogFragment = new SearchDialogFragment();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create popup with search feature
                searchDialogFragment.show(fm, "search");
            }
        });




    }



    public JSONObject updateStock(JSONObject stockJSON) {

        return stockJSON;
    }


    private static class MyThread extends Thread {
        @Override
        public void run() {
            //update stock info
        }
    }

}
