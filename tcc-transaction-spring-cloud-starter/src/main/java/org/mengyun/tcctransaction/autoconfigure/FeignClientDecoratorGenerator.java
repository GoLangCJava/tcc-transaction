package org.mengyun.tcctransaction.autoconfigure;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import javassist.bytecode.AttributeInfo;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author xiaobzhou
 * date 2019-03-08 13:58
 */
public class FeignClientDecoratorGenerator {

    public static Class generate(Class clientInterfaceClassType) throws NotFoundException, CannotCompileException {
        ClassPool cp = ClassPool.getDefault();

        CtClass clientInterface = cp.get(clientInterfaceClassType.getName());
        CtClass awareInterface = cp.get(TargetAware.class.getName());

        String decoratorClassPackage = clientInterfaceClassType.getPackage().getName() + ".decorator.";
        String feignClientDecoratorClass = decoratorClassPackage + clientInterfaceClassType.getSimpleName() + "Decorator0";

        CtClass decoratorCtClass = cp.makeClass(feignClientDecoratorClass);
        decoratorCtClass.setInterfaces(new CtClass[]{clientInterface, awareInterface});

        String fieldNameOfProxy = "target";
        CtField target = new CtField(clientInterface, fieldNameOfProxy, decoratorCtClass);
        target.setModifiers(Modifier.PRIVATE);
        decoratorCtClass.addField(target);

        CtMethod setTarget = awareInterface.getDeclaredMethods()[0];
        CtMethod setTargetImp = CtNewMethod.copy(setTarget, setTarget.getName(), decoratorCtClass, null);
        setTargetImp.setBody("{this." + fieldNameOfProxy + " = $1;}");
        decoratorCtClass.addMethod(setTargetImp);

        CtMethod[] clientMethods = clientInterface.getDeclaredMethods();
        for (CtMethod clientMethod : clientMethods) {

            CtMethod clientMethodImp = CtNewMethod.copy(clientMethod, clientMethod.getName(), decoratorCtClass, null);

            List srcAttrs = clientMethod.getMethodInfo().getAttributes();
            for (Object srcAttr : srcAttrs) {
                clientMethodImp.getMethodInfo().addAttribute((AttributeInfo) srcAttr);
            }
            clientMethodImp.setBody("{return this." + fieldNameOfProxy + "." + clientMethod.getName() + "($$);}");
            decoratorCtClass.addMethod(clientMethodImp);
        }
        Class decoratorClassType = decoratorCtClass.toClass();
        decoratorCtClass.detach();
        return decoratorClassType;
    }
}