package com.example.boy.connetwebserviceksoap2;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by boy on 2016/9/11.
 * Aim:initialize the NetTask Operate
 */

public class NetTask {

    private NetSetting NetSetting=new NetSetting();

    //initialize the Method in Package "ksoap2" and get the object form SoapSerializationEnvelope
    public SoapSerializationEnvelope DoGet(String MethodName, Map<String, String> Params){
        // 1、Define the WebService Space name and Method
        SoapObject request = new SoapObject(NetSetting.getNamespace()  , MethodName);
        //Set the Method's parameter if not exists then ignore it
        if (Params != null) {
            Iterator iter = Params.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                request.addProperty((String) entry.getKey(),
                        (String) entry.getValue());
            }
        }
        /*
         *   3、Create and use Webservice method to generate soap request
         *      parameter is the vserion name of Soap protocol and you can check it's Instructions to know it(WebService1.asmx?WSDL)
         */
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER12);
        envelope.bodyOut = request;
        // when you use donet protocol then use true
        envelope.dotNet = true;
        HttpTransportSE http = new HttpTransportSE(NetSetting.getURL());
        // use call() to connect Server
        try {
            http.call(null, envelope);
        } catch (HttpResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return envelope;
    }

    //Change Method according to the MethodName
    public String Distribute_WebService(String MethodName, Map<String, String> Params){
        if(MethodName.equals(NetSetting.getMethod_Hello()))
            return Hello_CallWebService(MethodName,Params);
        else{
            return Echo_CallWebService(MethodName,Params);
        }
    }


    //Hello_CallWebService
    public String Hello_CallWebService(String MethodName, Map<String, String> Params) {
        SoapSerializationEnvelope envelope=DoGet(MethodName,Params);
        try {
            final SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            if (result!= null) {
                Log.d("----ReceivedMessage----", result.toString());
                return result.toString();
            }

        } catch (SoapFault e) {
            Log.e("----WrongMessage---", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //Echo_CallWebService
    public String Echo_CallWebService(String MethodName, Map<String, String> Params) {
        SoapSerializationEnvelope envelope=DoGet(MethodName,Params);
        try{
            final SoapObject result = (SoapObject) envelope.getResponse();
            if (result!= null) {
                Log.d("----ReceivedMessage----", result.getProperty(0).toString());
                Log.d("----ReceivedMessage----", result.getProperty(1).toString());
                return result.toString();
            }

        } catch (SoapFault e) {
            Log.e("----WrongMessage---", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
