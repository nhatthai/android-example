package demo.nhatthai.cafegrapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhatthai on 6/21/16.
 */
public class GeoServiceImpl {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";
    private static GeoService service;

    public static GeoService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GeoService.class);
        return  service;
    }
}
