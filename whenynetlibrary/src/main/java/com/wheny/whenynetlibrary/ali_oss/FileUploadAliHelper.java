//package com.wheny.whenynetlibrary.ali_oss;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.alibaba.sdk.android.oss.ClientConfiguration;
//import com.alibaba.sdk.android.oss.ClientException;
//import com.alibaba.sdk.android.oss.OSS;
//import com.alibaba.sdk.android.oss.OSSClient;
//import com.alibaba.sdk.android.oss.ServiceException;
//import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
//import com.alibaba.sdk.android.oss.common.OSSConstants;
//import com.alibaba.sdk.android.oss.common.OSSLog;
//import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
//import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
//import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
//import com.alibaba.sdk.android.oss.common.utils.IOUtils;
//import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
//import com.alibaba.sdk.android.oss.model.PutObjectRequest;
//import com.alibaba.sdk.android.oss.model.PutObjectResult;
//import com.wheny.whenylibrary.utils.FileUtils;
//
//import org.json.JSONObject;
//
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class FileUploadAliHelper {
//
//
//
//    private static final String STS_URL = "http://47.90.85.211:7080";
//    private static final String OSS_FILE_PATH="android/dev/";
////    private static final String OSS_FILE_PATH="android/test/";
////    private static final String OSS_FILE_PATH="android/pro/";
//
//    private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
//
//    public static final String OSS_URL = "https://oss.jmjcm.com/";
//
//
//    private static OSS oss;
//    private static Context mContext;
//
//    private OnProgressListener onProgressListener;
//    private OSSAsyncTask task;
//
//    private String url;
//    private long fileSize;
//
//
//
//
//    private int currentPercent=0;
//
//
//    public static FileUploadAliHelper getInstance(){
//        return new FileUploadAliHelper();
//    }
//
//    public static void init(Context context){
//        mContext = context;
//
//        //该配置类如果不设置，会有默认配置，具体可看该类
//        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
//        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
//        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
//        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//        OSSLog.enableLog(); //这个开启会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv
//
//        OSSCredentialProvider credentialProvider = new OSSFederationCredentialProvider(){
//            @Override
//            public OSSFederationToken getFederationToken() throws ClientException {
//                try {
//                    URL stsUrl = new URL(STS_URL);
//                    HttpURLConnection conn = (HttpURLConnection) stsUrl.openConnection();
//                    InputStream input = conn.getInputStream();
//                    String jsonText = IOUtils.readStreamAsString(input, OSSConstants.DEFAULT_CHARSET_NAME);
//                    JSONObject jsonObjs = new JSONObject(jsonText);
//                    String ak = jsonObjs.getString("AccessKeyId");
//                    String sk = jsonObjs.getString("AccessKeySecret");
//                    String token = jsonObjs.getString("SecurityToken");
//                    String expiration = jsonObjs.getString("Expiration");
//                    return new OSSFederationToken(ak, sk, token, expiration);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        };
//        oss = new OSSClient(context, endpoint, credentialProvider, conf);
//    }
//
//
//    public void cancel(){
//        if(task!=null){
//            task.cancel();
//        }
//    }
//
//
//
//
//    public void upload(String file){
//        if(oss==null){
//            init(mContext);
//            return;
//        }
//
//        String uploadFilePath = file;
//
//        String ossPath = OSS_FILE_PATH+ FileUtils.getNameByPath(file);
//        url = OSS_URL+ossPath;
//        Log.d("Oss_url",url);
//
//        // 构造上传请求
//        PutObjectRequest put = new PutObjectRequest("jmj-pro", ossPath, uploadFilePath);
//        // 异步上传时可以设置进度回调
//        put.setProgressCallback((request, currentSize, totalSize) -> {
//
//            int percentInt = (int)(currentSize*100/totalSize);
//            if(percentInt>currentPercent) {
//                currentPercent = percentInt;
//                if(onProgressListener!=null){
//                    onProgressListener.onProgress(currentPercent);
//                }
//            }
//        });
//        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//
//
//            @Override
//            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                Log.e("PutObjectonSuccess", "UploadSuccess");
//                Log.e("PutObjectonSuccess", result.getETag());
//                if(onProgressListener!=null){
//                    onProgressListener.onSuccess();
//                }
//            }
//
//            @Override
//            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                // 请求异常
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                    if(onProgressListener!=null){
//                        onProgressListener.onFailure("网络连接异常");
//                    }
//                    return;
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                    if(onProgressListener!=null){
//                        onProgressListener.onFailure(serviceException.getErrorCode());
//                    }
//                    return;
//                }
//                if(onProgressListener!=null){
//                    onProgressListener.onFailure("未知异常");
//                }
//            }
//        });
//
//
//
//        // task.cancel(); // 可以取消任务
//
//        // task.waitUntilFinished(); // 可以等待直到任务完成
//    }
//
//
//    public void setOnProgressListener(OnProgressListener onProgressListener) {
//        this.onProgressListener = onProgressListener;
//    }
//
//    public interface OnProgressListener{
//
//        void onProgress(int percent);
//
//        void onSuccess();
//
//        void onFailure(String errMsg);
//
//        void onStart();
//
//    }
//
//    public String getUrl() {
//        return url;
//    }
//}
