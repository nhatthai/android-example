package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.service.FileUploadService;
import demo.nhatthai.cafegrapp.service.GeneratorService;
import demo.nhatthai.cafegrapp.utils.RealPathUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nhatthai on 6/23/16.
 */
public class ImageFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        Button btnChooseImg = (Button) view.findViewById(R.id.btn_choose_image);
        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGalleryImg();
            }
        });

        return view;
    }

    public void openGalleryImg(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Uri selectedImageUri = data.getData();
            uploadFile(selectedImageUri);
        }
    }

    private void uploadFile(Uri fileUri) {

        String pathImg = RealPathUtil.getRealPathFormURI(getContext(), fileUri);

        if (pathImg != null) {
            File file = new File(pathImg);

            //work upload file .jpg
            RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            // create upload service client
            FileUploadService service = GeneratorService.createService(
                    FileUploadService.class, null, null);

            //get extend file name (.png, .jpg, .jpeg)
            Map<String, RequestBody> map = new HashMap<>();
            map.put("file\"; filename=\"" + file.getName(), fbody);
            Call<ResponseBody> call = service.uploadImageExt(map);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    if (response.code() == 201) {
                        try {
                            String data = response.body().string();
                            Log.v("Upload", "success:" + data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.e("Error response: ", response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("Upload error:", t.getMessage());
                }
            });
        }
    }
}
