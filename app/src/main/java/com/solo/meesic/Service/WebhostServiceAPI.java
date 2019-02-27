package com.solo.meesic.Service;

public class WebhostServiceAPI {
    private static String base_url = "https://phatdroid94com.000webhostapp.com/Server/";
    public static WebhostService getService() {
        return RetrofitBaseClient.getClient(base_url).create(WebhostService.class);
    }
}
