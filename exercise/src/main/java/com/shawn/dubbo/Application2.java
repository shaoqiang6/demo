//package com.shawn.dubbo;
//
//public class Application2 {
//    public static String queryActionIdsByActivityId(String env,Long activityId, Integer activityType, String expectName) {
//
//        String path = "com.meicai.mac.api.ActivityApi";
//        String medthod = "queryActionIdsByActivityId";
//
//        ActivityActionIdsQueryParam param = new ActivityActionIdsQueryParam();
//        param.activityId = activityId;
//        param.activityType = activityType;
//
//        DubboUtil dubboUtil = new DubboUtil();
//        String responseString =  dubboUtil.run(env, path, medthod,
//                new String[] { "com.meicai.mac.param.ActivityActionIdsQueryParam" }, new Object[] { param },
//                "", "mac");
//
//
//        Boolean isFound = false;
//        if (responseString.contains(expectName)) {
//            isFound = true;
//        }
//        assertThat(isFound).isTrue();
//        return responseString;
//    }
//}
