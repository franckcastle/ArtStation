//package api;
//
//public class SendSms {
//    // Find your Account Sid and Token at twilio.com/user/account
//    public static final String ACCOUNT_SID = "ACd79f74a9650441539cb1a86910aac20f";
//    public static final String AUTH_TOKEN = "a3877704a1f5bf965e1b4f27cc6326bd";
//
//    public static void SendSms(String numero,String s) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message.creator(new PhoneNumber(numero),
//                new PhoneNumber("+13155993133"),
//                s).create();
//
//    }
//}
