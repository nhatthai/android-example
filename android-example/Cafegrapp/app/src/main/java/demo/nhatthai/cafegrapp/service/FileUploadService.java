package demo.nhatthai.cafegrapp.service;

import java.util.Map;

import okhttp3.ResponseBody;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by nhatthai on 6/23/16.
 */
public interface FileUploadService {

    @Multipart
    @POST("avatar")
    Call<ResponseBody> uploadImage(@Part("file\"; filename=\"image.jpg") RequestBody file);

    //upload Image(get extend file name .png, .jpg, .jpeg)
    @Multipart
    @POST("avatar")
    Call<ResponseBody> uploadImageExt(@PartMap Map<String, RequestBody> params);
}
