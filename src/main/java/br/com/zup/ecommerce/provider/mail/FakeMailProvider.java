package br.com.zup.ecommerce.provider.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeMailProvider implements MailProvider {

    private final Logger logger = LoggerFactory.getLogger(FakeMailProvider.class);

    @Override
    public void sendMail(Mail mail) {
        logger.info("Email successfully sent to " + mail.getTarget());

        logger.warn("[FAKE MAIL] - " + mail.toString());
    }
}
