package com.wheny.whenycompilerlib;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：RouteNoteEntity
 * 包名：com.wheny.whenycompilerlib
 * 创建时间：2022/6/29 19:22
 * 创建人： WhenYoung
 * 描述：
 **/
public class RouteNoteEntity {
    public String className;
    public String classType;
    public List<String> routes;
    public List<RouteParams> params;

    static class RouteParams {
        public String key;
        public String type;
//        public boolean required;
    }
}

