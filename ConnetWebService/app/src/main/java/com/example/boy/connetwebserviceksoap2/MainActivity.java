package com.example.boy.connetwebserviceksoap2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private NetTask mNetTask=new NetTask();
    private NetSetting mNetSetting=new NetSetting();

    TextView tvMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBtn();
        initTv();
    }

    private void initTv() {
        tvMessage = (TextView) this.findViewById(R.id.tvMessage);
    }

    // initialize Control Button
    private void initBtn() {
        View btnHelloWorld = this.findViewById(R.id.btnHelloWorld);
        btnHelloWorld.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Map<String, String> values = new HashMap<String, String>();
                values.put("msg", "It's android data");
                Request(mNetSetting.getMethod_Hello());
            }
        });

        View btnEchoMessage = this.findViewById(R.id.btnEchoMessage);
        btnEchoMessage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> values = new HashMap<String, String>();
                values.put("msg1", "It's android data one");
                values.put("msg2", "It's android data two");
                Request(mNetSetting.getMethod_Echo(), values);
            }
        });
    }

    //Push Request for WebService
    public void Request(Object... params) {
        new AsyncTask<Object, Object, String>() {
            //Change the Textview content
            String txt=new String();
            @Override
            protected String doInBackground(Object... params) {
                if (params != null && params.length == 2) {
                    txt= mNetTask.Distribute_WebService((String) params[0],
                            (Map<String, String>) params[1]);
                } else if (params != null && params.length == 1) {
                    txt= mNetTask.Distribute_WebService((String) params[0], null);
                } else {
                    return null;
                }
                return null;
            }

            protected void onPostExecute(String result) {
                tvMessage.setText("Information from Server : " + txt);
            };
        }.execute(params);

    }
}