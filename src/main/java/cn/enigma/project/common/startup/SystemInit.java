package cn.enigma.project.common.startup;

import cn.enigma.project.common.define.SystemDefine;
import cn.enigma.project.common.util.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luzh
 * Create: 2019-06-04 18:18
 * Modified By:
 * Description:
 */
@Slf4j
public class SystemInit {

    protected static final Lock lock = new ReentrantLock();

    @PostConstruct
    public void dataInitialing() {
        log.debug("{} dataInitialing...", getClass().getName());
        Class<? extends SystemInit> clazz = getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (isInitDataMethod(method)) {
                log.debug("do init: {}", clazz + "." + method.getName());
                new Thread(() -> {
                    String id = String.valueOf(SnowflakeIdWorker.getInstance(1L, 1L).nextId());
                    MDC.put(SystemDefine.HTTP_REQUEST_TRACE_ID, id);
                    MDC.put(SystemDefine.HTTP_REQUEST_SPAN_ID, id);
                    try {
                        method.invoke(this);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        log.error("by luzh-> {}:{}.", clazz.getName(), e.getMessage(), e);
                    } finally {
                        MDC.clear();
                    }
                }).start();
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
