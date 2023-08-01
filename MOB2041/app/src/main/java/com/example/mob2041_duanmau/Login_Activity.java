package com.example.mob2041_duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob2041_duanmau.DAO.NhanVienDAO;

public class Login_Activity extends AppCompatActivity {
    EditText edtUser,edtPass;
    Button btnCancel,btnLogin;
    CheckBox chkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        chkRemember = findViewById(R.id.chkRemember);

        // đọc user, pass trong SharedPreferences
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        edtUser.setText(pref.getString("edtUser",""));
        edtPass.setText(pref.getString("edtPass",""));
        chkRemember.setChecked(pref.getBoolean("REMEMBER",false));

        NhanVienDAO NhanVienDAO = new NhanVienDAO(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                if (NhanVienDAO.checkDangNhap(user,pass)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    RememberUser(user,pass,chkRemember.isChecked());
                    startActivity(new Intent(Login_Activity.this, MainActivity.class));
                } else {
                    Toast.makeText(Login_Activity.this, "Tài khoản và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void resetForm(View view){
        edtUser.setText("");
        edtPass.setText("");
    }
    public void RememberUser(String edtUser, String edtPass , boolean status){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status){
            editor.clear();
        }else{
            editor.putString("edtUser",edtUser);
            editor.putString("edtPass",edtPass);
            editor.putBoolean("REMEMBER",status);
        }
        //lưu lại
        editor.commit();
    }



}



