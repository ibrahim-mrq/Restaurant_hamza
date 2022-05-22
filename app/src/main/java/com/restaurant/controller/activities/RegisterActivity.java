package com.restaurant.controller.activities;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.orhanobut.hawk.Hawk;
import com.restaurant.DB.DatabaseAccess;
import com.restaurant.R;
import com.restaurant.databinding.ActivityRegisterBinding;
import com.restaurant.helpers.BaseActivity;
import com.restaurant.helpers.Constants;
import com.restaurant.model.User;

public class RegisterActivity extends BaseActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        binding.appbar.tvTool.setText(getString(R.string.login));
        binding.appbar.imgBack.setOnClickListener(view -> onBackPressed());

        binding.registerBtn.setOnClickListener(view -> {
            register();
        });
    }

    private void register() {
        if (isNotEmpty(binding.etEmail, binding.tvEmail)
                && isValidEmail(binding.etEmail, binding.tvEmail)
                && isNotEmpty(binding.etPassword, binding.tvPassword)
                && isNotEmpty(binding.etFName, binding.tvFName)
                && isNotEmpty(binding.etLName, binding.tvLName)
                && isNotEmpty(binding.etPhone, binding.tvPhone)
//                && isNotEmpty(binding.etPhone2, binding.tvPhone2)
                && isNotEmpty(binding.etGovernorate, binding.tvGovernorate)
                && isNotEmpty(binding.etNeighborhood, binding.tvNeighborhood)
//                && isNotEmpty(binding.etHouseNumber, binding.tvHouseNumber)
//                && isNotEmpty(binding.etNavigationalMark, binding.tvNavigationalMark)
        ) {
            enableElements(false);
            DatabaseAccess db = DatabaseAccess.getInstance(this);
            db.open();
            if (db.checkUser(getText(binding.etEmail), getText(binding.etPassword))) {
                enableElements(true);
                showErrorAlert(this, "", "هناك خطا! المستخدم موجود مسبقا!");
            } else {
                enableElements(true);
                User user = new User();
                user.setEmail(getText(binding.etEmail));
                user.setFirstName(getText(binding.etFName));
                user.setLastName(getText(binding.etLName));
                user.setPassword(getText(binding.etPassword));
                user.setPhone(getText(binding.etPhone));
                user.setGovernorate(getText(binding.etGovernorate));
                user.setNeighborhood(getText(binding.etNeighborhood));
                user.setNavigational(getText(binding.etNavigationalMark));
                user.setHouseNumber(getText(binding.etHouseNumber));
                user.setPhone2(getText(binding.etPhone2));

                db.insertUser(user);
                Hawk.put(Constants.IS_LOGIN, true);
                Hawk.put(Constants.USER, db.getUser(getText(binding.etEmail)));
                Hawk.put(Constants.USER_ID, db.getUser(getText(binding.etEmail)).getId());
                db.close();
                showAlert(this, "", "تم انشاء حساب جديد بنجاح");
                new Handler().postDelayed(() -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }, 2000);
            }
        }

    }

    private void enableElements(boolean enable) {
        binding.registerBtn.setEnabled(enable);
        if (!enable) {
            binding.registerBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_grey));
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.registerBtn.setBackground(getResources().getDrawable(R.drawable.shape_accent));
        }
        binding.appbar.imgBack.setEnabled(enable);
        binding.etEmail.setEnabled(enable);
        binding.etPassword.setEnabled(enable);
        binding.etFName.setEnabled(enable);
        binding.etLName.setEnabled(enable);
        binding.etPhone.setEnabled(enable);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}