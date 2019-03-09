package org.mengyun.tcctransaction.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author xiaobzhou
 * date 2019-03-07 22:47
 */
public class TccFeignClientProxyDecoratePostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return FeignClientDecoratorFactory.getDecorator(bean, beanName);
    }
}