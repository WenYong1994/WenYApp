package com.example.netlibrary.helper;

import com.example.commonlibrary.utils.LogUtils;
import com.example.netlibrary.bean.user.entity.UserInfo;
import com.example.netlibrary.bean.body.BaseRequest;
import com.example.netlibrary.bean.body.RequestBaseBody;
import com.google.gson.Gson;

public class RequestHelper {
    public static Gson gson;
    private static final String OS = "android";

    public static String toJson(BaseRequest data, UserInfo userInfo, String serviceName){
        if(gson==null){
            gson = new Gson();
        }
        if(userInfo != null){
            data.setUid(userInfo.getUid());
            data.setToken(userInfo.getToken());
            data.setUserId(userInfo.getUid());
            data.setPoster(userInfo.getPoster());
            data.setUserName(userInfo.getUserName());
            data.setUsername(userInfo.getUserName());
            data.setNickName(userInfo.getNickName());
            data.setNickname(userInfo.getNickName());
        }
        data.setOs(OS);

        String json = handlerRequest(data, serviceName);
        LogUtils.e("RequestHelper",json);

        return json;
    }


    public static String toJsonUnPoster(BaseRequest data, UserInfo userInfo, String serviceName){
        if(gson==null){
            gson = new Gson();
        }
        if(userInfo != null){
            data.setUid(userInfo.getUid());
            data.setToken(userInfo.getToken());
            data.setUserId(userInfo.getUid());
            data.setUserName(userInfo.getUserName());
        }
        data.setOs(OS);

        String json = handlerRequest(data, serviceName);
        LogUtils.e("RequestHelper",json);

        return json;
    }


    public static String toJsonSingle(BaseRequest data, UserInfo userInfo, String serviceName){
        if(gson==null){
            gson = new Gson();
        }
        if(userInfo != null){
            data.setUid(userInfo.getUid());
            data.setToken(userInfo.getToken());
        }
        data.setOs(OS);

        String json = handlerRequest(data, serviceName);
        LogUtils.e("RequestHelper",json);

        return json;
    }

    public static String toJson(BaseRequest data, UserInfo userInfo, String serviceName,boolean setUid){
        if(gson==null){
            gson = new Gson();
        }

        if(userInfo != null){
            data.setUid(userInfo.getUid());
            data.setToken(userInfo.getToken());
            data.setPoster(userInfo.getPoster());
            if(setUid){
                data.setUserId(userInfo.getUid());
                data.setUserName(userInfo.getUserName());
                data.setUsername(userInfo.getUsername());
                data.setNickName(userInfo.getNickName());
                data.setNickname(userInfo.getNickName());
            }
        }
        data.setOs(OS);
        String json = handlerRequest(data, serviceName);
        LogUtils.e("RequestHelper",json);
        return json;
    }


    public static String toJsonAddDepartment(BaseRequest data, UserInfo userInfo, String serviceName){
        if(gson==null){
            gson = new Gson();
        }

        if(userInfo != null){
            data.setUid(userInfo.getUid());
            data.setUserId(userInfo.getUid());
            data.setToken(userInfo.getToken());
            data.setDepartmentId(userInfo.getDepartmentId());
            data.setDepartmentName(userInfo.getDepartmentName());
            data.setPoster(userInfo.getPoster());
            data.setUserName(userInfo.getUserName());
            data.setUsername(userInfo.getUserName());
            data.setNickName(userInfo.getNickName());
            data.setNickname(userInfo.getNickName());
        }
        data.setOs(OS);
        String json = handlerRequest(data, serviceName);
        LogUtils.e("RequestHelper",json);
        return json;
    }

    static String handlerRequest(BaseRequest data, String serviceName) {
        RequestBaseBody requestBaseBody = new RequestBaseBody();
        requestBaseBody.setChannel("android");
        requestBaseBody.setVersion("1.0");
        requestBaseBody.setTime(System.currentTimeMillis() / 1000);
        requestBaseBody.setTest(true);
        requestBaseBody.setSalt("064797");
        requestBaseBody.setService(serviceName);
        String dataStr = gson.toJson(data);
        requestBaseBody.setData(dataStr);
        return gson.toJson(requestBaseBody);
    }

}
