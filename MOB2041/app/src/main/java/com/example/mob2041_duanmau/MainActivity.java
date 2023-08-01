package com.example.mob2041_duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob2041_duanmau.DAO.NhanVienDAO;
import com.example.mob2041_duanmau.fragment.QLLoaiSanFragment;
import com.example.mob2041_duanmau.fragment.QLHoaDonFragment;
import com.example.mob2041_duanmau.fragment.QLSanFragment;
import com.example.mob2041_duanmau.fragment.QLThanhVienFragment;
import com.example.mob2041_duanmau.fragment.ThongKeDoanhThuFragment;
import com.example.mob2041_duanmau.fragment.ThongKeTop10Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
         FrameLayout frameLayout = findViewById(R.id.frameLayout);
         NavigationView navigationView = findViewById(R.id.navigationView);
         drawerLayout = findViewById(R.id.drawerLayout);
         View headerLayout = navigationView.getHeaderView(0);
        TextView txtTen = headerLayout.findViewById(R.id.txtTen);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar(); // Chuyển đổi actionbar = toolbar
        actionBar.setDisplayHomeAsUpEnabled(true);//Hiển thị nút
        actionBar.setHomeAsUpIndicator(R.drawable.ic_reshot_icon_menu_z9xkujngw2);//add icon

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.mQLPhieuMuon:
                        fragment = new QLHoaDonFragment();
                        break;
                    case R.id.mQLLoaiSach:
                        fragment = new QLLoaiSanFragment();
                        break;
                    case R.id.mExit:
                        Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    case R.id.mDoiMatKhau:
                        showDialogDoiMatKhau();
                        break;
                    case R.id.mTop10:
                        fragment = new ThongKeTop10Fragment();
                        break;
                    case R.id.mQLThanhVien:
                        fragment = new QLThanhVienFragment();
                        break;
                    case R.id.mDoanhThu:
                        fragment = new ThongKeDoanhThuFragment();
                        break;
                    case R.id.mQLSach:
                        fragment = new QLSanFragment();
                        break;
                        default:
                        fragment = new QLHoaDonFragment();
                        break;
                }

                if (fragment != null){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();

                    drawerLayout.closeDrawer(GravityCompat.START);
                    toolbar.setTitle(item.getTitle());
                }
                return false;
            }
        });

        //Hiển thị 1 số chức năng của admin
        SharedPreferences sharedPreferences = getSharedPreferences("THONG TIN",MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan","");
        if(!loaiTK.equals("Admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mTop10).setVisible(false);
        }
        String hoten = sharedPreferences.getString("hoten","");
        txtTen.setText("Welcome, "+hoten);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    private void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("Cập Nhật",null)
                .setPositiveButton("Hủy",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doi_mk,null);
        EditText edtOldPass = view.findViewById(R.id.edtPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtNewPass);
        EditText edtReNewPass = view.findViewById(R.id.edtReNewPass);


        builder.setView(view);


        AlertDialog alertDialog = builder.create() ;
        alertDialog.setCancelable(false); // ko cho click ra ngoài dialog
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String ReNewPass = edtReNewPass.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || ReNewPass.equals("")){
                    Toast.makeText(MainActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    if (newPass.equals(ReNewPass)){
                        SharedPreferences sharedPreferences = getSharedPreferences("THONG TIN", MODE_PRIVATE);
                        String matt = sharedPreferences.getString("matt","");
                        //Cap Nhat
                        NhanVienDAO thuThuDAO = new NhanVienDAO(MainActivity.this);
                        int check = thuThuDAO.capNhatMatKhau(matt, oldPass, newPass);
                        if (check == 1){
                            Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,Login_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else if (check == 0){
                            Toast.makeText(MainActivity.this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Nhập mật khẩu không trùng với nhau", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}