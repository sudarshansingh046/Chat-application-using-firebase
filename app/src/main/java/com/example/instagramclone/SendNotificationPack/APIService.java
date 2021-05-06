package com.example.instagramclone.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAgidZUOk:APA91bEMDx_PI09dXZUTpspFzbAZ9MpjJbbY-MYFF49fOs4sdMbSRiKmGjbsAOdMaXy18ckEeoCttXn4-F-BZ7axCQrem0LZ4DYyAYrJVvslrpYYzkgp-f3AFjb95Yjp4WD8IhD9QQeP" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);

}

