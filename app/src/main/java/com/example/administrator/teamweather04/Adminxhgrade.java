package com.example.administrator.teamweather04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adminxhgrade extends AppCompatActivity {
    Spinner sp_term;
    String termid="";
    Button bt_sh;
    ListView lv_grade;
        EditText sp_xh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminxhgrade);
        setTitle("按班级查询成绩");
        sp_term = (Spinner)findViewById(R.id.sp_term);
       ;
        bt_sh = (Button)findViewById(R.id.bt_sh);
        lv_grade = (ListView)findViewById(R.id.lv_grade);
        sp_xh=(EditText)findViewById(R.id.sp_xh);
        GetTerm getTerm =new GetTerm(); //获取学期信息列表
        getTerm.start();

        bt_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetGrade getGrade = new GetGrade();
                getGrade.start();
            }
        });

    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    fillTerm(msg.obj);
                    break;

                case 4:
                    fillGrade(msg.obj);
                    break;
            }
        }
    };

    private void fillTerm(Object obj){
        List<TermList> list = (List<TermList>)obj;
        ArrayAdapter<TermList> arrayAdapter = new ArrayAdapter<TermList>(Adminxhgrade.this,
                android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_term.setAdapter(arrayAdapter);
        sp_term.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                termid = ((TermList)parent.getSelectedItem()).id; //学期id
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                termid="";
            }
        });
    }


    private void fillGrade(Object obj){
        ArrayList<Map<String,Object>> inf = (ArrayList<Map<String,Object>>)obj;
        SimpleAdapter simpleAdapter = new SimpleAdapter(Adminxhgrade.this,inf,R.layout.admin_get_person_grade,
                new String[]{"km_name","cj"},new int[]{R.id.tv_km,R.id.tv_cj});
        lv_grade.setAdapter(simpleAdapter);
    }
    class GetTerm extends Thread{
        @Override
        public void run() {
            String url = NetWorkInf.apiUrl+"getterm.php";
            String result = NetWorkInf.netResponse(url);
            try{
                JSONArray jsonArray = new JSONArray(result);
                List<TermList>list = new ArrayList<TermList>();
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject per = (JSONObject)jsonArray.opt(i);
                    TermList t = new TermList();
                    t.id = per.getString("id");
                    t.term = per.getString("term");
                    t.No = per.getString("No");
                    list.add(t);
                }
                Message msg = new Message();
                msg.what =1;
                msg.obj = list;
                handler.sendMessage(msg);

            }catch (Exception ee){
                ee.printStackTrace();
            }

        }
    }


    class GetGrade extends Thread{
        @Override
        public void run() {
            String xh=sp_xh.getText().toString();
            String url = NetWorkInf.apiUrl+"admingetpersongrade.php?tid="+termid+"&xh="+xh;
            String result = NetWorkInf.netResponse(url);
            try{
                JSONArray jsonArray = new JSONArray(result);
                StringBuffer sbf = new StringBuffer();
                ArrayList<Map<String,String>> inf =new ArrayList<Map<String, String>>();
                String km_name,cj;//学号，姓名，科目，成绩临时变量
                for(int i=0;i<jsonArray.length();i++){
                    Map<String,String>item = new HashMap<String, String>();
                    km_name= ((JSONObject)jsonArray.opt(i)).getString("km_name");
                    cj= ((JSONObject)jsonArray.opt(i)).getString("cj");



                    item.put("km_name",km_name);

                    item.put("cj",cj);
                    inf.add(item);
                }
                Message msg = new Message();
                msg.what = 4;
                msg.obj = inf;
                handler.sendMessage(msg);

            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }

}
