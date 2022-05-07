package com.restaurant.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.databinding.ActivityLoginBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.appbar.tvTool.setText(getString(R.string.login));

        binding.tvRegister.setOnClickListener(view -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        binding.loginBtn.setOnClickListener(view -> {
            login();
        });
    }

    private void login() {
        if (isNotEmpty(binding.etEmail, binding.tvEmail)
                && isValidEmail(binding.etEmail, binding.tvEmail)
                && isNotEmpty(binding.etPassword, binding.tvPassword)
        ) {
            enableElements(false);
            DatabaseAccess db = DatabaseAccess.getInstance(this);
            db.open();
            if (db.checkUser(getText(binding.etEmail), getText(binding.etPassword))) {
                showAlert(this, "", "تم تسجيل الدخول بنجاح");
                Hawk.put(Constants.IS_LOGIN, true);
                Hawk.put(Constants.USER, db.getUser(getText(binding.etEmail)));
                Hawk.put(Constants.USER_ID, db.getUser(getText(binding.etEmail)).getId());
                Log.e("response", "id = " + db.getUser(getText(binding.etEmail)).getId());
                db.close();
                new Handler().postDelayed(() -> {
                    enableElements(true);
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }, 2000);
            } else {
                enableElements(true);
                showErrorAlert(this, "", "هناك خطا! يرجى التاكد من البيانات!");
            }
        }

    }

    private void enableElements(boolean enable) {
        binding.loginBtn.setEnabled(enable);
        if (!enable) {
            binding.loginBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_grey));
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.loginBtn.setBackground(getResources().getDrawable(R.drawable.shape_accent));
        }
        binding.etEmail.setEnabled(enable);
        binding.etPassword.setEnabled(enable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}