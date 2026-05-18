package com.example.demo.config;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import com.twilio.Twilio;

@Configuration
public class TwilioConfig {

    private final Dotenv dotenv = Dotenv.load();

    @PostConstruct
    public void initTwilio() {
        Twilio.init(
                dotenv.get("TWILIO_ACCOUNT_SID"),
                dotenv.get("TWILIO_AUTH_TOKEN")
        );
    }

    public String getVerifySid() {
        return dotenv.get("TWILIO_VERIFY_SID");
    }

    public String getFromNumber() {
        return dotenv.get("TWILIO_PHONE_NUMBER");
    }

    public String getAccountSid() {
        return dotenv.get("TWILIO_ACCOUNT_SID");
    }
}