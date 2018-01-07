package com.example.administrator.teamweather04;

import android.app.Activity;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


public class ModPwd extends AppCompatActivity {
      Button btn_lg;
    EditText et_oldpwd,et_pwd,et_pwddo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_pwd);
        setTitle("修改密码");
        btn_lg=(Button)findViewById(R.id.btn_lg) ;
        et_oldpwd=(EditText)findViewById(R.id.et_oldpwd);
        et_pwd=(EditText)findViewById(R.id.et_pwd);
        et_pwddo=(EditText)findViewById(R.id.et_pwddo);
        btn_lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean ha=NetWorkInf.checkpwd(et_oldpwd.getText().toString(), et_pwd.getText().toString(), et_pwddo.getText().toString(),ModPwd.this);
               if(ha) {
                   Getresult getresult = new Getresult();
                   getresult.start();
               }
            }
        });
    }
    class  Getresult extends Thread{
          @Override
          public void run() {
              String user=NetWorkInf.user;
              String url = NetWorkInf.apiUrl+"ModPwd.php?pwd="+et_pwd.getText().toString()+"&user="+user;
              String rs = NetWorkInf.netResponse(url);
              try{
                  JSONObject jsonObject = new JSONObject(rs);
                  String lg = jsonObject.getString("login");
                  if(lg.equalsIgnoreCase("true")){

                      Intent intent = new Intent();
                      intent.setClass(ModPwd.this,MainActivity.class);
                      startActivity(intent);
                      Looper.prepare();
                      Toast.makeText(ModPwd.this,"修改成功",Toast.LENGTH_LONG).show();
                      Looper.loop();

                  }else{
                      Looper.prepare();
                      Toast.makeText(ModPwd.this,"修改失败",Toast.LENGTH_LONG).show();
                      Looper.loop();
                  }


              }catch (Exception ee){
                  ee.printStackTrace();
                  Looper.prepare();
                  Toast.makeText(ModPwd.this,rs,Toast.LENGTH_LONG).show();
                  Looper.loop();
              }





          }
    }

}
