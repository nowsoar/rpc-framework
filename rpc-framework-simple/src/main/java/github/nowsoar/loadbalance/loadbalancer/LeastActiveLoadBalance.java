package github.nowsoar.loadbalance.loadbalancer;

import github.nowsoar.loadbalance.AbstractLoadBalance;
import github.nowsoar.loadbalance.RpcStatus;
import github.nowsoar.remoting.dto.RpcRequest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LeastActiveLoadBalance extends AbstractLoadBalance {
    @Override
    protected String doSelect(List<String> serviceAddresses, RpcRequest rpcRequest) {
        int length = serviceAddresses.size();
        int leastActive = -1;
        int leastCount = 0;
        int[] leastIndexes = new int[length];
        int[] weights = new int[length];
        int totalWeight = 0;
        int firstWeight = 0;
        boolean sameWeight = true;
        for (int i = 0; i < length; i ++) {
            String serviceAddress = serviceAddresses.get(i);
            int active = RpcStatus.getStatus(serviceAddress, rpcRequest.getRpcServiceName()).getActive();
            if (leastActive == -1 || active < leastActive) {
                leastActive = active;
                leastCount = 1;
                leastIndexes[0] = i;
            } else if (active == leastActive) {
                leastIndexes[leastCount++] = i;
            }
        }
        if (leastCount == 1) {
            return serviceAddresses.get(leastIndexes[0]);
        }
        return serviceAddresses.get(leastIndexes[ThreadLocalRandom.current().nextInt(leastCount)]);
    }
}
