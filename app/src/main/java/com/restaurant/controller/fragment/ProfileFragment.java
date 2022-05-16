package com.restaurant.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;
import com.restaurant.databinding.FragmentProfileBinding;
import com.restaurant.helpers.BaseFragment;
import com.restaurant.helpers.Constants;
import com.restaurant.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProfileFragment extends BaseFragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    FragmentProfileBinding binding;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        User user = Hawk.get(Constants.USER);
        binding.etFName.setText(user.getFirstName());
        binding.etLName.setText(user.getLastName());
        binding.etEmail.setText(user.getEmail());
        binding.etPhone.setText(user.getPhone());
        binding.etPassword.setText(user.getPassword());

        binding.etCity.setText(user.getCity());
        binding.etAddress.setText(user.getAddress());
        binding.etNearest.setText(user.getNearest());


        binding.btnUpdate.setOnClickListener(view -> {

        });
    }
}