package greenart.trade.mebmer.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private static final String sendEmail = "dieneid13@gmail.com";
    private static int number;

    private static void createNumber() {
        number = (int)(Math.random() * (90000)) + 100000;
    }

    public MimeMessage createMessage(String email) {
        createNumber();
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(sendEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증입니다.");
            String body = "";
            body += "<h3>" + "요청한 이메일 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "utf-8", "html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public int sendMail(String email) {
        MimeMessage message = createMessage(email);
        mailSender.send(message);

        return number;
    }
}
