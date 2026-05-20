package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import com.example.demo.services.TwilioService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/twilio")
public class TwilioController {

    private final TwilioService service;

    public TwilioController(TwilioService service) {
        this.service = service;
    }

    // SEND OTP
    @PostMapping("/otp/send")
    public String sendOtp(@RequestParam String phone) {
        return service.sendOtp(phone);
    }

    // VERIFY OTP + RETURN JWT
    @PostMapping("/otp/verify")
    public Map<String, Object> verifyOtp(
            @RequestParam String phone,
            @RequestParam String code
    ) {

        boolean verified = service.verifyOtp(phone, code);

        Map<String, Object> response = new HashMap<>();

        if (verified) {

            String token = JwtUtil.generateToken(phone);

            response.put("success", true);
            response.put("token", token);
            response.put("phone", phone);

        } else {

            response.put("success", false);
            response.put("message", "Invalid OTP");
        }

        return response;
    }

    // PROTECTED ROUTE
    @PostMapping("/sms")
    public String sendSms(
            @RequestParam String phone,
            @RequestParam String message
    ) {
        return service.sendSms(phone, message);
    }
}