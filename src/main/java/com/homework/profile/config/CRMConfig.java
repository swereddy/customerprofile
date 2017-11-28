package com.homework.profile.config;


import com.homework.profile.crm.CRMClient;
import com.homework.profile.crm.FeignCRMClient;
import com.homework.profile.crm.MockCRMClientImpl;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CRMConfig {

    @Value("${gateway.crm.baseUri}")
    private String crmBaseUri;


    @Bean
    public FeignCRMClient crmClient() {
        FeignCRMClient crmClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignCRMClient.class, crmBaseUri);

        return  crmClient;
    }

}
