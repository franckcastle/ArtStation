package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {
        // Find your Account Sid and Token at twilio.com/user/account
        public static final String ACCOUNT_SID = "ACe09d66a6c3389c629930cfc1dccbd6c6";
        public static final String AUTH_TOKEN = "669f57565a2d594e0fe736f0f03ac5b6";

        public static void SendSms(String numero,String s) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber(numero),
                    new PhoneNumber("+13155993133"),
                    s).create();

    }
}