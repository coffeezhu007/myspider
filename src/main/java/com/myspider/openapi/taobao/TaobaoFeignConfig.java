package com.myspider.openapi.taobao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Slf4j
public class TaobaoFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        ErrorDecoder delegate = new ErrorDecoder.Default();
        ObjectReader objectReader = new ObjectMapper().readerFor(String.class);

        return (methodKey, response) -> {
            if (response.status() == 500) {
                try {
                    String error = objectReader.readValue(response.body().asInputStream());
                    log.error("[Myspider] remote invocation error:" + error);
                    //throw new RemoteInvocationException(ErrorCodeEnum.POINT_EXCHANGE_FAIL);
                } catch (IOException e) {
                    log.warn("[Myspider] Could not read error details");
                    //throw new RemoteInvocationException(ErrorCodeEnum.POINT_EXCHANGE_FAIL);
                }
            }
            return delegate.decode(methodKey, response);
        };
    }
}
