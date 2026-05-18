package com.example.demo.controller;

import com.example.demo.services.TwilioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/twilio")
public class TwilioController {

    private final TwilioService service;

    public TwilioController(TwilioService service) {
        this.service = service;
    }

    // ----------------------------
    // SEND OTP
    // ----------------------------
    @PostMapping("/otp/send")
    public String sendOtp(@RequestParam String phone) {
        return service.sendOtp(phone);
    }

    // ----------------------------
    // VERIFY OTP
    // ----------------------------
    @PostMapping("/otp/verify")
    public boolean verifyOtp(
            @RequestParam String phone,
            @RequestParam String code
    ) {
        return service.verifyOtp(phone, code);
    }

    // ----------------------------
    // SEND SMS
    // ----------------------------
    @PostMapping("/sms")
    public String sendSms(
            @RequestParam String phone,
            @RequestParam String message
    ) {
        return service.sendSms(phone, message);
    }
}