package org.mengyun.tcctransaction.autoconfigure;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author xiaobzhou
 * date 2019-03-07 23:30
 */
public class FeignClientDecoratorFactory {

    private static final Logger LOG = LoggerFactory.getLogger(FeignClientDecoratorFactory.class);

    public static Object getDecorator(Object bean, String beanName) {
        Object wrapBean = bean;
        try {
            // @FeignClient标注的接口被注入spring容器时,名称为接口全类名
            Class classType = Class.forName(beanName);
            Annotation fcAnnotation = classType.getAnnotation(FeignClient.class);
            if (null != fcAnnotation) {
                Method[] methods = classType.getDeclaredMethods();
                boolean isFeignClientNeedDecorated = false;
                for (Method method : methods) {
                    Annotation compAnnotation = method.getAnnotation(Compensable.class);
                    if (null != compAnnotation) {
                        isFeignClientNeedDecorated = true;
                        break;
                    }
                }
                if (isFeignClientNeedDecorated) {
                    wrapBean = createDecorator(bean, classType);
                    LOG.debug("FeignClient proxy bean class(" + beanName + ") " +
                            "was wrapped again by tcc transaction as class(" + wrapBean.getClass() + ")");
                }
            }
        } catch (ClassNotFoundException ignored) {
            //do noting 忽略bean名称不是全类型构成的bean报出的ClassNotFoundException
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return wrapBean;
    }

    private static Object createDecorator(Object bean, Class classType)
            throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {

        Class decoratorClassType = FeignClientDecoratorGenerator.generate(classType);
        Object wrapBean = decoratorClassType.newInstance();
        ((TargetAware) wrapBean).setTarget(bean);
        return wrapBean;
    }
}