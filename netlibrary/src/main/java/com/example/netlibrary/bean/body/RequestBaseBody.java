package com.example.netlibrary.bean.body;


/**
 * Auto-generated: 2019-12-10 16:46:58
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class RequestBaseBody {

    private String channel;
    private String data;
    private String ip;
    private String salt;
    private String service;
    private boolean test;
    private long time;
    private String version;

    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getChannel() {
        return channel;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getSalt() {
        return salt;
    }

    public void setService(String service) {
        this.service = service;
    }
    public String getService() {
        return service;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
    public boolean getTest() {
        return test;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public long getTime() {
        return time;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersion() {
        return version;
    }

    public Object getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isTest() {
        return test;
    }
}