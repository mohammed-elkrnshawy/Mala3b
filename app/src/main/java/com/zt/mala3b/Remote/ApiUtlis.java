package com.zt.mala3b.Remote;

/**
 * Created by Mahmoud on 11/11/2018.
 */

public class ApiUtlis {

    public static final String Base_Url="http://mla3b.website/api/";


    public static UserService getUserService()
    {
        return RetrofitClient.getClient(Base_Url).create(UserService.class);
    }
}
