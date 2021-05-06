package com.example.instagramclone.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService2 {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAgidZUOk:APA91bHzfxBQXwYqAQZnhD9sj-8zyd-SQcyofqy49FPsp9VUnGDMJiAKmBYbIzo4rtqkMWxXvxwO-Yt7X1N-GgmhG_h36YmcMl5lgOLMq2OE46fbD9LBYXVgAMuavn54M0E1KDyh_p_V" // Your server key refer to video for finding your server key
            }
    )


    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender sender);
}
