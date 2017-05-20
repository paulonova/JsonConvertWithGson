package com.example.android.restful;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.restful.model.DataItem;
import com.example.android.restful.services.MyService;
import com.example.android.restful.utils.NetworkHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    public List<DataItem> dataItemList;

    private boolean networkOk;
    TextView output;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            String message = intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);

            DataItem[] dataItems = (DataItem[]) intent.getParcelableArrayExtra(MyService.MY_SERVICE_PAYLOAD);
            dataItemList = Arrays.asList(dataItems);

            for (int i = 0; i < dataItemList.size() ; i++) {
                output.append(dataItemList.get(i).getItemName() + "\n");
                Log.d("MainActivity" , "DataList: " + dataItemList.get(i).getImage());
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataItemList = new ArrayList<>();

        output = (TextView) findViewById(R.id.output);

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mBroadcastReceiver,
                        new IntentFilter(MyService.MY_SERVICE_MESSAGE));

        networkOk = NetworkHelper.hasNetworkAccess(this);
        output.append("Network ok: " + networkOk);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }

    public void runClickHandler(View view) {

        if (networkOk) {
            Intent intent = new Intent(this, MyService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        } else {
            Toast.makeText(this, "Network not available!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearClickHandler(View view) {
        output.setText("");
    }

}
