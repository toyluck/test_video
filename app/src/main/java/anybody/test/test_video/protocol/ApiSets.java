package anybody.test.test_video.protocol;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by anybody on 2016/9/27.
 *
 */
public interface ApiSets {

    @Multipart
    @POST
    Call<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);
}
