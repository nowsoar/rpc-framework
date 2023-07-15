import github.nowsoar.HelloService;
import github.nowsoar.annotation.RpcScan;
import github.nowsoar.config.RpcServiceConfig;
import github.nowsoar.remoting.transport.netty.server.NettyRpcServer;
import github.nowsoar.serviceimpl.HelloServiceImpl2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RpcScan(basePackage = {"github.nowsoar"})
public class NettyServerMain {
    public static void main(String[] args) {
        // Register service via annotation
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyServerMain.class);
        NettyRpcServer nettyRpcServer = (NettyRpcServer) applicationContext.getBean("nettyRpcServer");

        // Register service manually
        // HelloService helloService2 = new HelloServiceImpl2();
        // RpcServiceConfig rpcServiceConfig = RpcServiceConfig.builder()
        //         .group("test2").version("version2").service(helloService2).build();
        // NettyRpcServer nettyRpcServer = new NettyRpcServer();
        // nettyRpcServer.registerService(rpcServiceConfig);
        nettyRpcServer.start();
    }
}
