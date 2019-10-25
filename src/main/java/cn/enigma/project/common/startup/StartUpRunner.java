package cn.enigma.project.common.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luzh
 * Create: 2019-10-25 15:26
 * Modified By:
 * Description:
 */
@Slf4j
@Component
@Order(0)
public class StartUpRunner implements CommandLineRunner {

    protected static final Lock lock = new ReentrantLock();

    protected final Executor executor;

    public StartUpRunner(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("{} start up ...", getClass().getName());
        Class<? extends StartUpRunner> clazz = getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (isInitDataMethod(method)) {
                log.debug("start up do init: {}", clazz + "." + method.getName());
                Runnable runnable = () -> {
                    try {
                        method.invoke(this);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        log.error("by luzh-> {}:{}.", clazz.getName(), e.getMessage(), e);
                    }
                };
                executor.execute(runnable);
                Thread.sleep(100);
            }
        }
    }

    private boolean isInitDataMethod(Method method) {
        return method.getName().startsWith("init")  // 方法以init开头
                && Modifier.isPublic(method.getModifiers())  // public方法
                && Void.TYPE.equals(method.getReturnType())  // 返回类型为void
                && !method.isVarArgs()  // 无参数
                && !Modifier.isAbstract(method.getModifiers());  // 非抽象方法
    }
}
