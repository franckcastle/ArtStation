package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSms {
        // Find your Account Sid and Token at twilio.com/user/account
        public static final String ACCOUNT_SID = "ACd79f74a9650441539cb1a86910aac20f";
        public static final String AUTH_TOKEN = "8a21f7c2643b15dcc2f52dc1a413eaf6";

        public static void SendSms(String numero,String s) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber(numero),
                    new PhoneNumber("+16074007909"),
                    s).create();

    }
}