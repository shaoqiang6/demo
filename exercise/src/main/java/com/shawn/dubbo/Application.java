package com.shawn.dubbo;

import com.sprucetec.spc.api.PriceApi;
import com.sprucetec.spc.api.dto.PriceDto;
import com.sprucetec.spc.api.param.QueryPriceParam;
import com.sprucetec.spc.api.result.SpcApiResult;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.List;

public class Application {
    private static String zookeeperHost = System
            .getProperty("zookeeper.address", "192.168.2.26");
    private static String zookeeperPort = System.getProperty("zookeeper.port",
            "2181");

    public static void main(String[] args) {
        ReferenceConfig<PriceApi> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig(
                "zookeeper://" + zookeeperHost + ":" + zookeeperPort));
        reference.setInterface(PriceApi.class);
        reference.setGroup("spc");
        PriceApi service = reference.get();
        QueryPriceParam param = new QueryPriceParam();
        SpcApiResult<List<PriceDto>> listSpcApiResult = service.queryBySsuIdList(param);
        System.out.println(listSpcApiResult);
    }
}
