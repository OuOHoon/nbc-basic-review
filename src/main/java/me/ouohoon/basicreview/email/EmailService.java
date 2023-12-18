package me.ouohoon.basicreview.email;

import lombok.RequiredArgsConstructor;
import me.ouohoon.basicreview.email.dto.Email;
import me.ouohoon.basicreview.global.exception.BaseException;
import me.ouohoon.basicreview.global.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String from;

    public void send(Email email) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(from);
        mail.setTo(email.getTo());
        mail.setSubject(email.getSubject());
        mail.setText(email.getContent());

        try {
            javaMailSender.send(mail);
        } catch (MailException e) {
            throw new BaseException(ErrorCode.FAIL_TO_SEND_EMAIL);
        }
    }
}
