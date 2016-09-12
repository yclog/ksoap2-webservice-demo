package com.example.boy.connetwebserviceksoap2;

/**
 * Created by boy on 2016/9/11.
 * Content:NetTask Setting
 */
public class NetSetting {

    public NetSetting(){};

    //Method Name
    final String METHOD_HELLO_WORLD = "HelloWorld";
    final String METHOD_ECHO_MESSAGE = "EchoMessage";

    //Server Url 
    final String WEB_SERVICE_URL = "http://Ip/WebService1.asmx";
    //WebService Space Name
    final String Namespace = "http://tempuri.org/";
    //Get Space Name
    public String getNamespace(){
        return Namespace;
    }
    //Get WebService Url
    public String getURL(){
        return WEB_SERVICE_URL;
    }
    //Get Mthod Name "HelloWorld"
    public String getMethod_Hello(){
        return METHOD_HELLO_WORLD;
    }
    //Get Mthod Name "EchoMessage"
    public String getMethod_Echo(){
        return METHOD_ECHO_MESSAGE;
    }
}
