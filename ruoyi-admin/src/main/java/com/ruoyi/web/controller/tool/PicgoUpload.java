package com.ruoyi.web.controller.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PicgoUpload {

    public static String uploadByPicgo(List<String> sourceUrls) throws Exception {
        // 构建请求体
        Map<String, List<String>> requestMap = new HashMap<>();
        requestMap.put("list", sourceUrls); // 直接使用传入的 sourceUrls
        String jsonBody = new Gson().toJson(requestMap);

        // 发送请求
        String url = "http://127.0.0.1:36677/upload";
        OkHttpClient client = new OkHttpClient();
        okhttp3.RequestBody body = okhttp3.RequestBody.create(
                MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            // 解析响应
            Map map = new Gson().fromJson(responseData, Map.class);
            Boolean isSuccess = (Boolean) map.get("success");
            if (isSuccess) {
                // 获取所有返回的 URL 并用逗号连接
                List<String> resultUrls = (List<String>) map.get("result");
                return String.join(",", resultUrls);
            } else {
                throw new Exception("上传失败");
            }
        } finally {
            // 清理资源
            client.dispatcher().executorService().shutdown();
        }
    }
}
