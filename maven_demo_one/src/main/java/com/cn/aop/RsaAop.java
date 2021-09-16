package com.cn.aop;

import com.cn.utils.RsaUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RsaAop {

    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNOuU7MX5/NfgzStO7KwF+Q8Hbplf/cJahQuzeA+UUKjuX0ePWbwtHwRGkCj6ZkqijSLLqlbRgFcWeyHYfQmxinWFvq8LARQ3aiaGa8FyU+yffNSwfnJ/Qb0WYTmGgG/d2L5LKP/nRBJ8WiPnnqB0ZVS0Onv+NDVQgGLJGqFqVlwIDAQAB";
    public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI065Tsxfn81+DNK07srAX5DwdumV/9wlqFC7N4D5RQqO5fR49ZvC0fBEaQKPpmSqKNIsuqVtGAVxZ7Idh9CbGKdYW+rwsBFDdqJoZrwXJT7J981LB+cn9BvRZhOYaAb93Yvkso/+dEEnxaI+eeoHRlVLQ6e/40NVCAYskaoWpWXAgMBAAECgYA/fMtRmcVIwGUsAaY+zG1mamU6LYP/c4hzQ9ehUlTnhLi3XgYy/kYu4m7gtC5B8TjX9jVf2R511zO4VlsrYDvCSfj5G/wXDEM57JwdNNCl6+lUPynV2uSZZTxrnv3rX38PY2AMr3Lco5fkuM8xqcJIPAohqVrqmVJ62zdsMt2a+QJBAMTQVdvmURRd1MonvTvBWxYcG8Nsojy/ItXYSaSnELlkYmF+i8rit9a+q2p1FVZJicAJn7axWqNdhqXh+BbvYq0CQQC3s3VNt6bO7ckUYV8/nOEiwZs1N3BVgdbDQDx/mzSTn2raJ1NTOzp0syKMJ4cI3I30cO/jtCaTqczrNCLtVmXTAkA/7gx1qZKcsv3zzITDbENSIz9Bc4Afi1adb+w796iPD/SFBr4R4SMmNjkoE3Ai3zBHmNiwV1vBxRd/uq8DhC7hAkEAlwSUOcjx/AmllfiaqhwZRU5ufyhNVC5CVQ+Y7Lq2VWnoGOHdcX4uh0PA8gGI1HMkzECQJdyzHg+oo++fiwfM+wJAEbjwT9Glls49ZkGw4lHxTP0yIeIFSfKVrhwEGXv19MzEOeyDmUbTxYo0hKVLaLez1Io/qmdv6R8hAayJEFp9nw==";


    @Pointcut(value = "@annotation(com.cn.aop.Rsa)")
    public void cut(){}

    /*@Before(value = "cut()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        args[0] = "hls";
    }*/

    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        try {
            //对入参进行私钥解密
            args[0] = RsaUtils.decrypt((String) args[0], privateKey);
            //执行接口逻辑并返回结果
            Object proceed = joinPoint.proceed(args);
            //对结果进行公钥加密
            return RsaUtils.encrypt((String)proceed, publicKey);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


}
