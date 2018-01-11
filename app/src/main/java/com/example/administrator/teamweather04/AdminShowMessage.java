package com.example.administrator.teamweather04;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.administrator.teamweather04.R.id.lv_grade;

public class AdminShowMessage extends AppCompatActivity {
    ListView lv_grade;
    EditText sp_xh;
Button bt_sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_message);
        lv_grade = (ListView) findViewById(R.id.lv_grade);
        sp_xh = (EditText) findViewById(R.id.sp_xh);
        bt_sh=(Button)findViewById(R.id.bt_sh);
        bt_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Getmessage getmessage = new Getmessage();
                getmessage.start();
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 4:
                    fillGrade(msg.obj);
                    break;
            }
        }
    };

    private void fillGrade(Object obj) {
        ArrayList<Map<String, Object>> inf = (ArrayList<Map<String, Object>>) obj;
        SimpleAdapter simpleAdapter = new SimpleAdapter(AdminShowMessage.this, inf, R.layout.admin_xh_xm_xb_class,
                new String[]{"xh", "xm", "sex", "class_id"}, new int[]{R.id.tv_xh, R.id.tv_xm, R.id.tv_sex, R.id.tv_class_id});
        lv_grade.setAdapter(simpleAdapter);
    }

    class Getmessage extends Thread {
        @Override
        public void run() {

            String user = sp_xh.getText().toString();
            String url = NetWorkInf.apiUrl + "getmessage.php?user=" + user;
            String result = NetWorkInf.netResponse(url);
            try {
                JSONArray jsonArray = new JSONArray(result);
                StringBuffer sbf = new StringBuffer();
                ArrayList<Map<String, String>> inf = new ArrayList<Map<String, String>>();
                String xh, xm, sex, class_id;//学号，姓名，科目，成绩临时变量
                for (int i = 0; i < jsonArray.length(); i++) {
                    Map<String, String> item = new HashMap<String, String>();
                    xh = ((JSONObject) jsonArray.opt(i)).getString("xh");
                    xm = ((JSONObject) jsonArray.opt(i)).getString("xm");

                    sex = ((JSONObject) jsonArray.opt(i)).getString("sex");
                    class_id = ((JSONObject) jsonArray.opt(i)).getString("class_id");
                    item.put("xh", xh);
                    item.put("xm", xm);
                    item.put("sex", sex);
                    item.put("class_id", class_id);
                    inf.add(item);
                }
                Message msg = new Message();
                msg.what = 4;
                msg.obj = inf;
                handler.sendMessage(msg);

            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }
}
