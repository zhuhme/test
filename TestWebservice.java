package com.zhu;


import com.alibaba.fastjson.JSON;
import org.apache.axis2.rpc.client.RPCServiceClient;

import javax.xml.namespace.QName;
import java.net.URL;

public class TestWebservice {
    public static void axis() {
        try {
            // 指出service所在完整的URL
            String endpoint = "http://WebXml.com.cn/webservice/getSupportProvince?wsdl";
            //调用接口的targetNamespace
            String targetNamespace = "http://webservice接口所在的包名，逆序，一直到src下";

            RPCServiceClient serviceClient = new RPCServiceClient();
            //所调用接口的方法method
            String method = "所要调用的方法名";
            // 创建一个服务(service)调用(call)
            Service service = new Service();
            Call call = (Call) service.createCall();// 通过service创建call对象
            // 设置service所在URL
            call.setTargetEndpointAddress(String.valueOf(new URL(endpoint)));
            call.setOperationName(new QName(targetNamespace, method));
            call.setUseSOAPAction(setUseSOAPAction);

            //变量最好只是用String类型，其他类型会报错
            call.addParameter(String.valueOf(new QName(targetNamespace, "变量名")), XMLType.XSD_STRING,ParameterMode.IN);//设置参数名 state  第二个参数表示String类型,第三个参数表示入参
            call.setReturnType(XMLType.XSD_STRING);// 设置返回类型
            // String path = targetNamespace + method;
            // call.setSOAPActionURI(path);
            String jsonString = (String) call.invoke(new Object[] {"变量值"});//此处为数组，有几个变量传几个变量
            //将json字符串转换为JSON对象
            JSON json = (JSON) JSON.parse(jsonString);
            //将接送对象转为java对象,此处用object代替，用的时候转换为你需要是用的对象就行了
            Object object = JSON.toJavaObject(json, Object.class);//注意别到错包com.alibaba.fastjson.JSON
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
