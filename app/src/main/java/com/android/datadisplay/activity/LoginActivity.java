package com.android.datadisplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.datadisplay.R;
import com.android.datadisplay.bean.UserBean;
import com.android.datadisplay.jdbc.MyDao;
import com.android.datadisplay.utils.ToastUtil;


public class LoginActivity extends AppCompatActivity {
    private EditText et_user;
    private EditText et_pwd;
    private Button bt_login;
    private String user = "";
    private String pwd = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bt_login.setClickable(false);
                bt_login.setText("正在登录");
                user = et_user.getText().toString().trim();
                pwd = et_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(user)) {
                    ToastUtil.toast("用户名不能为空");
                    bt_login.setClickable(true);
                    bt_login.setText("登录");
                    return;
                }
                if (TextUtils.isEmpty(user)) {
                    ToastUtil.toast("请输入密码");
                    bt_login.setClickable(true);
                    bt_login.setText("登录");
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final UserBean userBean = MyDao.login(user,pwd);

                               if (userBean !=null){
                                   Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                   Bundle bundle = new Bundle();
                                   bundle.putSerializable("userBean",userBean);
                                   intent.putExtras(bundle);
                                   startActivity(intent);
                                   finish();
                               }else {
                                    ToastUtil.toast2UI(LoginActivity.this,"用户名或密码错误");
                                   LoginActivity.this.runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           bt_login.setClickable(true);
                                           bt_login.setText("登录");
                                       }
                                   });
                                   return;
                               }
                       }
                }).start();

            }
        });
    }

    private void initView() {
        et_user =  findViewById(R.id.et_user);
        et_pwd =  findViewById(R.id.et_pwd);
        bt_login =  findViewById(R.id.bt_login);
    }
}
