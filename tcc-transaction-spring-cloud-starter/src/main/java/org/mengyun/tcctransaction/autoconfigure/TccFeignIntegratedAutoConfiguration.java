package org.mengyun.tcctransaction.autoconfigure;

import feign.Feign;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaobzhou
 * date 2019-03-07 22:54
 */
@Configuration
@ConditionalOnClass({Feign.class, FeignClient.class})
@EnableConfigurationProperties(TccFeignConfigProperties.class)
@ConditionalOnProperty(value = "tcc.feign.enabled", matchIfMissing = true)
public class TccFeignIntegratedAutoConfiguration {

    @Bean
    public TccFeignClientProxyDecoratePostProcessor tccFeignClientProxyDecoratorPostProcessor() {
        return new TccFeignClientProxyDecoratePostProcessor();
    }
}