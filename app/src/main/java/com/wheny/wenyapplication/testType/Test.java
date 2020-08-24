package com.wheny.wenyapplication.testType;

import java.lang.reflect.ParameterizedType;

public class Test {
    public static void main(String[] args) {
//        AbstractDao<String> dao = new AbstractDao<String>();
        AbstractDao dao = new UserDao();
        System.out.println(getActualType(dao));
    }


    public static Class getActualType(Object obj) {
        ParameterizedType parameterizedType = (ParameterizedType) obj.getClass().getGenericSuperclass();
        return (Class) parameterizedType.getActualTypeArguments()[0];
    }
}
 class AbstractDao<T> {

}




class UserDao extends AbstractDao<String> {

}