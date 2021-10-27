//package com.shawn.dubbo;
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.ReferenceConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.rpc.service.GenericService;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.dubbo.common.json.JSON;
//
///**
// * @Author: huliting
// */
//public class DubboUtil {
//    // 创建dubbo注册中心
//    public RegistryConfig registry = new RegistryConfig();
//    public ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//    public GenericService genericService;
//
//    public String run(String env,String path,String methodName,String[] type,Object[] param,String version){
//        String zk = Constant.getZKIp(env);
//        System.out.println(zk);
//        RegistryConfig registry = new RegistryConfig();
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//        GenericService genericService;
//
//        // 普通编码配置方式
//        ApplicationConfig application = new ApplicationConfig();
//        application.setName("dubboUtil");
//
//        // 连接注册中心配置
//        registry.setAddress("zookeeper://"+zk);
//
//        reference.setGeneric(true);
//        reference.setApplication(application);
//        reference.setRegistry(registry);
//        reference.setGeneric(true); // 声明为泛化接口
//
//
//        // 配置接口路径
//        reference.setInterface(path);
//        // 设置接口版本
//        reference.setVersion(version);
//        genericService = reference.get();
//
//
//        // 基本类型以及Date,List,Map等不需要转换，直接调用
//        Object result = genericService.$invoke(methodName, type, param);
//
//        // 将result toJSONString。直接打印打印的是rd的toString方法，一般rd会将toString方法重写。
//        String ss = JSON.toJSONString(result);
//
//        return ss;
//
//    }
//    public String run(String env,String path,String methodName,String[] type,Object[] param,String version,String group){
//        String zk = Constant.getZKIp(env);
//        System.out.println("----------"+zk);
//        RegistryConfig registry = new RegistryConfig();
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//        GenericService genericService;
//
//        // 普通编码配置方式
//        ApplicationConfig application = new ApplicationConfig();
//        application.setName("dubboUtil");
//
//        // 连接注册中心配置
//        registry.setAddress("zookeeper://"+zk);
//
//        reference.setGeneric(true);
//        reference.setApplication(application);
//        reference.setRegistry(registry);
//        reference.setGeneric(true); // 声明为泛化接口
//
//
//        // 配置接口路径
//        reference.setInterface(path);
//        // 设置接口版本
//        if(StringUtils.isNotEmpty(version)){
//            reference.setVersion(version);
//        }
//        if(StringUtils.isNotEmpty(group)){
//            reference.setGroup(group);
//        }
//        genericService = reference.get();
//
//
//        // 基本类型以及Date,List,Map等不需要转换，直接调用
//        Object result = genericService.$invoke(methodName, type, param);
//
//        // 将result toJSONString。直接打印打印的是rd的toString方法，一般rd会将toString方法重写。
//        String ss = JSON.toJSONString(result);
//
//        return ss;
//
//    }
//}
