package br.com.zup.ecommerce.config;


import br.com.zup.ecommerce.provider.file.CloudImageUpload;
import br.com.zup.ecommerce.provider.file.FakeImageProvider;
import br.com.zup.ecommerce.provider.file.UploadImageProvider;
import br.com.zup.ecommerce.provider.mail.FakeMailProvider;
import br.com.zup.ecommerce.provider.mail.MailProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProviderConfiguration {

    @Profile("dev")
    @Bean
    public UploadImageProvider devImageProvider() {
        return new FakeImageProvider();
    }

    @Profile("prod")
    @Bean
    public UploadImageProvider prodImageProvider() {
        return new CloudImageUpload();
    }

    @Bean
    public MailProvider mailProvider() {
        return new FakeMailProvider();
    }

}
