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


    //    private static final String username = "abo.mahroq@gmail.com";
//    private static final String password = "Anas@8102005";
    private static final String username = "seven.arts.ku@gmail.com";
    private static final String password = "fneyqxlrzjjdqbsi";
    private static final String toEmailList = "abo.mahroq@gmail.com,abu.mahroq@hotmail.com";
    private static final String emailSubject = "subject";
    private static final String emailTitle = "title";
    private static final String emailContact = "contact";
    private static final String emailBody = "Hello";

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

        binding.btnUpdate.setOnClickListener(view -> {
            sendEmail();
        });
    }

    Boolean state;

    private void sendEmail() {

        Handler handler = new Handler(message -> {
            done(state);
            return false;
        });

        new Thread(() -> {
            try {

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.BCC,
                            InternetAddress.parse(toEmailList));
                    message.setSubject("(" + emailSubject + ") " + emailTitle);
                    message.setText("بيانات التواصل: " + emailContact + "\n\n الرسالة:\n" + emailBody);
                    Transport.send(message);
                    state = true;


                } catch (MessagingException e) {
                    e.printStackTrace();
                    Log.e("response", "MessagingException = " + e.getLocalizedMessage());
                    state = false;
                }

                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("response", "Exception = " + e.getLocalizedMessage());
            }
        }).start();


    }

    private void done(boolean state) {
        Log.e("response", "state = " + state);
    }
}