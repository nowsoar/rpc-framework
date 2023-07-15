package github.nowsoar.loadbalance;

import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description:
 * @author: ZKP
 * @time: 2022/10/30
 */
@Data
public class RpcStatus {

    private int active = 0;
    private long total = 0;
    private static ConcurrentMap<String, ConcurrentMap<String, RpcStatus>> METHOD_STATISTICS = new ConcurrentHashMap<>();


    public static RpcStatus getStatus(String serviceAddress, String serviceName) {
        ConcurrentMap<String, RpcStatus> map = METHOD_STATISTICS.computeIfAbsent(serviceAddress,
                (k) -> new ConcurrentHashMap<>());
        return map.computeIfAbsent(serviceName, (k) -> new RpcStatus());
    }

    public static boolean beginCount(String serviceAddress, String serviceName) {
        RpcStatus methodStatus = getStatus(serviceAddress, serviceName);
        if (methodStatus.active == 2147483647) {
            return false;
        } else {
            methodStatus.active ++;
            return true;
        }
    }

    public static void endCount(String serviceAddress, String serviceName) {
        RpcStatus methodStatus = getStatus(serviceAddress, serviceName);
        methodStatus.active --;
        methodStatus.total ++;
    }
}
