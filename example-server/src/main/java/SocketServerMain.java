import github.nowsoar.HelloService;
import github.nowsoar.config.RpcServiceConfig;
import github.nowsoar.registry.zk.util.CuratorUtils;
import github.nowsoar.remoting.transport.socket.SocketRpcServer;
import github.nowsoar.serviceimpl.HelloServiceImpl;

public class SocketServerMain {
    public static void main(String[] args) {
        try {
            // CuratorUtils.getZkClient().delete().deletingChildrenIfNeeded().forPath("/my-rpc/github.javaguide.HelloService");
            System.out.println(CuratorUtils.getZkClient().getChildren().forPath("/my-rpc/github.javaguide.HelloService"));
            // System.out.println("/my-rpc/" +rpcServiceConfig.getRpcServiceName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        HelloService helloService = new HelloServiceImpl();
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        rpcServiceConfig.setService(helloService);
        socketRpcServer.registerService(rpcServiceConfig);
        try {
            // CuratorUtils.getZkClient().delete().deletingChildrenIfNeeded().forPath("/my-rpc/github.javaguide.HelloService");
            System.out.println(CuratorUtils.getZkClient().getChildren().forPath("/my-rpc/github.javaguide.HelloService"));
            // System.out.println("/my-rpc/" +rpcServiceConfig.getRpcServiceName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        socketRpcServer.start();
    }
}
