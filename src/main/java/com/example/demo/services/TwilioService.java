package com.example.demo.services;

import com.example.demo.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final TwilioConfig config;

    public TwilioService(TwilioConfig config) {
        this.config = config;
    }

    // ----------------------------
    // DEBUG
    // ----------------------------
    public void debug() {
        System.out.println("ACCOUNT SID: " + config.getAccountSid());
        System.out.println("VERIFY SID: " + config.getVerifySid());
        System.out.println("FROM NUMBER: " + config.getFromNumber());
    }

    // ----------------------------
    // SEND SMS
    // ----------------------------
    public String sendSms(String toPhone, String message) {

        Message.creator(
                new PhoneNumber(toPhone),
                new PhoneNumber(config.getFromNumber()),
                message
        ).create();

        return "SMS sent successfully";
    }

    // ----------------------------
    // SEND OTP
    // ----------------------------
    public String sendOtp(String phoneNumber) {

        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+" + phoneNumber;
        }

        Verification.creator(
                config.getVerifySid(),
                phoneNumber,
                "sms"
        ).create();

        return "OTP sent successfully";
    }

    // ----------------------------
    // VERIFY OTP
    // ----------------------------
    public boolean verifyOtp(String phoneNumber, String code) {

        if (!phoneNumber.startsWith("+")) {
            phoneNumber = "+" + phoneNumber;
        }

        VerificationCheck verificationCheck =
                VerificationCheck.creator(config.getVerifySid())
                        .setTo(phoneNumber)
                        .setCode(code)
                        .create();

        return "approved".equals(verificationCheck.getStatus());
    }
}