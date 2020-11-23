package com.boolmall.server.controller;

import com.boolmall.server.Service.FirstPageService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
@SpringBootApplication
@RequestMapping("/first")
public class FirstPageController {
    private static Logger log = Logger.getLogger(FirstPageController.class.toString());

    @RequestMapping(value = "/conPy",method = RequestMethod.POST)
    public String conPyFormdata() throws JSONException, IOException {
        String retString="111111";
        String url = "http://0.0.0.0:8000/tttformdata";

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        JSONObject jsonParam = new JSONObject();

        JSONObject credentialsJsonParam = new JSONObject();

        credentialsJsonParam.put("scope", "GLOBAL");
        //注意，如果ID一样的话，插入失败
        credentialsJsonParam.put("id", "id");
        credentialsJsonParam.put("username", "abc520");
        credentialsJsonParam.put("password", "123456");
        credentialsJsonParam.put("description", "hello world jenkins hellow");
        credentialsJsonParam.put("$class", "com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl");

        jsonParam.put("credentials", credentialsJsonParam);
        jsonParam.put("", "0");

        log.info("=============:\t" + jsonParam.toString());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("json", jsonParam.toString(), ContentType.MULTIPART_FORM_DATA);

        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        HttpResponse resp = null;
        resp = client.execute(httpPost);

        return retString;
    }



}
