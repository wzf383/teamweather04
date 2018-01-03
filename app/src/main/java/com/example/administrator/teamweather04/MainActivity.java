package com.example.administrator.teamweather04;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText et_user,et_pwd;
    Button bt_lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("登录");

        et_user = (EditText)findViewById(R.id.et_user);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        bt_lg = (Button)findViewById(R.id.btn_lg);
        et_user.setText("07110801");
        et_pwd.setText("123456");
        bt_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login(et_user.getText().toString(),et_pwd.getText().toString());
                login.start();
            }
        });
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 123:
                    hlogin();
                    break;
            }
        }
    };
    private void hlogin(){
        String user = et_user.getText().toString();
        if(user.equalsIgnoreCase("admin")){
            NetWorkInf.user=et_user.getText().toString();
            NetWorkInf.pwd = et_pwd.getText().toString();

            Intent intent = new Intent();
            intent.setClass(MainActivity.this,AdminAC.class);
            startActivity(intent);

        }else{
            NetWorkInf.user=et_user.getText().toString();
            NetWorkInf.pwd = et_pwd.getText().toString();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,UserAc.class);
            startActivity(intent);

        }
        this.finish();
    }
    private class Login extends Thread{
        private String user = "";
        private String pwd = "";
        public Login(String user,String pwd){
            this.user = user;
            this.pwd = pwd;

        }
        @Override

        public void run() {
            String url = NetWorkInf.apiUrl+"login.php?user="+user+"&pwd="+pwd;
            String rs = NetWorkInf.netResponse(url);
            try{
                JSONObject jsonObject = new JSONObject(rs);
                String lg = jsonObject.getString("login");
                if(lg.equalsIgnoreCase("false")){
                    Looper.prepare();
                    Toast.makeText(MainActivity.this,"登录失败，用户名或密码错误",Toast.LENGTH_LONG).show();
                    Looper.loop();

                }else{


                    Message msg = new Message();
                    msg.what =123;
                    handler.sendMessage(msg);
                }


            }catch (Exception ee){
                ee.printStackTrace();
                Looper.prepare();
                Toast.makeText(MainActivity.this,rs,Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }
    }
}