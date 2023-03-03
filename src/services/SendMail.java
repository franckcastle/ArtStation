package services;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
public class SendMail {
    static String username ="artstation2223@gmail.com";
    static String password ="fvnqagwswpclhjst";

    public static void sendMail(String to,String subject,String msg){

            Properties pros = new Properties();
            pros.put("mail.smtp.auth",true);
            pros.put("mail.smtp.starttls.enable",true);
            pros.put("mail.smtp.host","smtp.gmail.com");
            pros.put("mail.smtp.port","587");
Session session = Session.getInstance(pros, new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username,password);
    }
});
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            
        }catch (Exception ex){
            System.out.println(ex);
        }

    }



}