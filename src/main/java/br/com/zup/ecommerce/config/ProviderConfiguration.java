package br.com.zup.ecommerce.config;


import br.com.zup.ecommerce.provider.CloudImageUpload;
import br.com.zup.ecommerce.provider.FakeImageProvider;
import br.com.zup.ecommerce.provider.UploadImageProvider;
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

}
