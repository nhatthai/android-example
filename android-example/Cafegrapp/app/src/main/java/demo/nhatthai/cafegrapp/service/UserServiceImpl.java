package demo.nhatthai.cafegrapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhatthai on 6/20/16.
 */
public class UserServiceImpl {
    private static final String BASE_URL = "https://api.github.com/";
    private static UserService service;

    public static UserService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(UserService.class);
        return  service;
    }

}
