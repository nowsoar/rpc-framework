package github.nowsoar.serviceimpl;

import github.nowsoar.Hello;
import github.nowsoar.HelloService;
import github.nowsoar.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RpcService(group = "test2", version = "version2")
public class HelloServiceImplZKP1 implements HelloService {

    static {
        System.out.println("HelloServiceImplZKP1被创建");
    }

    @Override
    public String hello(Hello hello) {
        log.info("HelloServiceImplZKP1收到：{}.", hello.getMessage());
        String result = "New String:" + hello.getMessage();
        log.info("HelloServiceImplZKP1返回：{}.", result);
        return result;
    }
}
