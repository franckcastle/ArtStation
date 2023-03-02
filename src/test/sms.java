package test;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class sms {
        // Find your Account Sid and Token at twilio.com/user/account
        public static final String ACCOUNT_SID = "ACe09d66a6c3389c629930cfc1dccbd6c6";
        public static final String AUTH_TOKEN = "111e4d93728f446914b695b09dc15c68";

        public static void main(String[] args) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Message message = Message.creator(new PhoneNumber("+21627005915"),
                    new PhoneNumber("+13155993133"),
                    "test").create();

            System.out.println(message.getSid());
    }
}