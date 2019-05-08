package sdk.facecamera.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sdk.facecamera.sdk.pojos.FaceInfo;
import sdk.facecamera.sdk.pojos.QueryFaceModel;
import sdk.facecamera.sdk.sdk.ComHaSdkLibrary;
import sdk.facecamera.sdk.sdk.FaceFlags;
import sdk.facecamera.sdk.sdk.FaceImage;
import sdk.facecamera.sdk.sdk.FaceRecoInfo;
import sdk.facecamera.sdk.sdk.HA_LiveStream;
import sdk.facecamera.sdk.sdk.QueryCondition;
import sdk.facecamera.sdk.sdk.QueryFaceInfo;
import sdk.facecamera.sdk.sdk.ha_rect;
import sdk.facecamera.sdk.utils.H264Decoder;
import sdk.facecamera.sdk.utils.StringUtil;

public class FaceSdk {
    private static final String TAG = "FaceSdk";

    static{
        try{
            //  FIXME 需要load以下的so文件
            System.loadLibrary("hasdk");
            System.loadLibrary("jnidispatch");
            ComHaSdkLibrary.INSTANCE.HA_Init();
            ComHaSdkLibrary.INSTANCE.HA_SetNotifyConnected(1);
            ComHaSdkLibrary.INSTANCE.HA_InitFaceModel((String) null);
        }catch (Exception ex) {
            Log.e("none", "static initializer: ", ex);
        }
    }
    private static FaceSdk mFaceSdk;
    private Context mContext;
    private volatile boolean _played = false;
    private boolean initialized = false;
    private ComHaSdkLibrary.HA_Cam mCamera;

    private H264Decoder mDecoder;
    private FaceInfoCallBack mFaceInfoCb;
    private Object mQueryObj = new Object();
    private static int mFrameWidth = 1920;
    private static int mFrameHeight = 1080;

    private List<QueryFaceModel> faceModelList;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            QueryFaceModel model = (QueryFaceModel) msg.obj;
            if (mQueryCb != null){
                mQueryCb.onQueryById(true,model);
            }
            if(mQueryPageCallBack != null && faceModelList != null){
                mQueryPageCallBack.onQueryPageCallBack(true,faceModelList); }
        }
    };

    public interface FaceInfoCallBack{
        /**
         * 人脸数据回调 运行在子线程中
         * @param inside true 对比度大于0 在人脸库里
         * @param info modle
         * @param errMsg 错误信息
         */
        void onFaceInfoResult(boolean inside,FaceInfo info,String errMsg);
    }
    public void setFaceInfoCallBack(FaceInfoCallBack callBack){
        this.mFaceInfoCb = callBack;
    }

    /**
     * 相机连接事件回调
     */
    private ConnectCallBack mConnectCb;
    public interface ConnectCallBack{
        void onConnected(String ip, short port, int usrParam);
        void onDisConnected(String ip, short port, int usrParam);
    }
    public void setConnectCallBack(ConnectCallBack callBack){
        this.mConnectCb = callBack;
    }

    /**
     * 查询事件回调
     */
    private QueryCallBack mQueryCb;
    public interface QueryCallBack{
        void onQueryById(boolean success,QueryFaceModel model);
    }
    public void setQueryCallBack(QueryCallBack callBack){
        this.mQueryCb = callBack;
    }

    //分页查询回调
    private QueryPageCallBack  mQueryPageCallBack;

    public interface QueryPageCallBack{
        void  onQueryPageCallBack(boolean success,List<QueryFaceModel> modelList);
    }

    public void setQueryPageCallBack(QueryPageCallBack queryPageCallBack){
        this.mQueryPageCallBack = queryPageCallBack;
    }

    /**
     * 查询事件回调
     */
    private HaveFaceCallBack mHaveFaceCb;
    public interface HaveFaceCallBack{
        void onFaceSuccess(byte[] faceImg,byte[] twistImg);
        void onFaceFaild(int errorcode);
    }
    public void setHaveFaceCallBack(HaveFaceCallBack callBack){
        this.mHaveFaceCb = callBack;
    }

    private HA_FaceRecoCb faceRecoCb = new HA_FaceRecoCb();
    private HA_LiveStreamCb streamDataCb = new HA_LiveStreamCb();
    public HA_ConnectEventCb connectEventCb = new HA_ConnectEventCb();
    private HA_FaceQueryCb faceQueryCb = new HA_FaceQueryCb();
    public class HA_FaceRecoCb implements ComHaSdkLibrary.HA_FaceRecoCb_t{
        @Override
        public void apply(ComHaSdkLibrary.HA_Cam cam, FaceRecoInfo faceRecoInfo, Pointer usrParam) {
            FaceInfo info = new FaceInfo();
            try {
                if (faceRecoInfo.matched > 0) {
                    Log.e("LOG_TAG","HA_DataReadCb1");
                    String personId = StringUtil.byte2String(faceRecoInfo.matchPersonId);
                    String personName =StringUtil.byte2String(faceRecoInfo.matchPersonName);
//                    info.setCaptureTime(new Date(System.currentTimeMillis()));
                    info.setId(personId);
                    info.setName(personName);
                    info.setAge(faceRecoInfo.age);
                    info.setSex(faceRecoInfo.sex);
                    info.setRole(faceRecoInfo.matchRole);
                    info.setRealtime(faceRecoInfo.isRealtimeData != 0);
                    info.setStandardNum(faceRecoInfo.qValue);
                    info.setMatchScore(faceRecoInfo.matched);
                    //特写图
                    info.setFeatureRectInBg(faceRecoInfo.faceXInFaceImg,faceRecoInfo.faceYInFaceImg,
                            faceRecoInfo.faceWInFaceImg,faceRecoInfo.faceHInFaceImg);
                    //全景图
                    info.setFaceRegionInEnvironment(faceRecoInfo.faceXInImg,faceRecoInfo.faceYInImg,
                            faceRecoInfo.faceXInImg+faceRecoInfo.faceWInImg,faceRecoInfo.faceYInImg+faceRecoInfo.faceHInImg);
                    if (faceRecoInfo.existImg != 0){
                        info.setEnvironmentImageData(faceRecoInfo.img.getByteArray(0,faceRecoInfo.imgLen));
                    }
                    //模板图
                    info.setModelImageData(faceRecoInfo.modelFaceImg.getByteArray(0,faceRecoInfo.modelFaceImgLen));

                    if (faceRecoInfo.existFaceImg != 0){
                        info.setCaptureImageData(faceRecoInfo.faceImg.getByteArray(0,faceRecoInfo.faceImgLen));
                    }

                    info.setFeatureData(new float[faceRecoInfo.feature_size]);
                    for (int i = 0; i < faceRecoInfo.feature_size; i++) {
                        info.getFeatureData()[i] = faceRecoInfo.feature.getValue();
                    }

                    if (mFaceInfoCb != null){
                        mFaceInfoCb.onFaceInfoResult(true,info,null);
                    }
                }else {
                    if (mFaceInfoCb != null){
                        mFaceInfoCb.onFaceInfoResult(false,info,null);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                if (mFaceInfoCb != null){
                    mFaceInfoCb.onFaceInfoResult(false,info,e.getMessage());
                }
            }
        }
    }

    public class HA_LiveStreamCb implements ComHaSdkLibrary.HA_LiveStreamCb_t {
        @Override
        public void apply(ComHaSdkLibrary.HA_Cam cam, String ip, HA_LiveStream stream, int usrParam) {
            int stream_len = stream.streamLen;
            byte[] h264 = stream.streamBuf.getByteArray(0, stream_len);
            mFrameWidth = stream.w;
            mFrameHeight = stream.h;
            if (mContext == null){
                throw new NullPointerException("you need to initialzie sdk");
            }
            if (_played){
                mDecoder.handleH264(h264);
            }
        }
    }

    public static int getFrameWidth(){
        return mFrameWidth;
    }
    public static int getFrameHeight(){
        return mFrameHeight;
    }

    public class HA_ConnectEventCb implements ComHaSdkLibrary.HA_ConnectEventCb_t {

        @Override
        public void apply(ComHaSdkLibrary.HA_Cam cam, String ip, short port, int event, int usrParam) {
            Log.d(TAG, "连接状态：" + event);
            if (event == 1){
                if (mConnectCb != null){
                    mConnectCb.onConnected(ip,port,usrParam);
                }
            }else if (event == 2){
                if (mConnectCb != null){
                    mConnectCb.onDisConnected(ip,port,usrParam);
                }
            }
        }
    }



    public class HA_FaceQueryCb implements ComHaSdkLibrary.HA_FaceQueryCb_t{
        private QueryFaceModel info;
        @Override
        public void apply(ComHaSdkLibrary.HA_Cam cam, QueryFaceInfo faceQueryInfo, Pointer usrParam) {
            Log.i(TAG, "apply: record no" + faceQueryInfo.record_no +"; record_count" + faceQueryInfo.record_count);
            if (faceQueryInfo.record_no == 0){
                Message message = Message.obtain();
                message.obj = info;
                mHandler.sendMessage(message);
                Log.e(TAG, "查询完成，返回");
                return;
            }
            try {
                info = new QueryFaceModel();
                String personId = StringUtil.byte2String(faceQueryInfo.personID);
                String personName =StringUtil.byte2String(faceQueryInfo.personName);
                info.setId(personId);
                info.setName(personName);
                info.setRole(faceQueryInfo.role);
                info.setWiegandNo(faceQueryInfo.wgCardNO);
                info.setEffectStartTime(faceQueryInfo.effectStartTime);
                info.setEffectTime(faceQueryInfo.effectTime);
                byte[][] imageData = new byte[faceQueryInfo.imgNum][];
                info.setImageData(imageData);
                for (int i = 0; i < faceQueryInfo.imgNum; i++) {
                    int size = faceQueryInfo.imgSize[i];
                    Pointer pointer = faceQueryInfo.imgBuff[i];
                    imageData[i] = pointer.getByteArray(0,size);
                }
                info.setImageData(imageData);
                info.setFeatureCount(faceQueryInfo.feature_count);
                info.setFeatureSize(faceQueryInfo.feature_size);
                //float[][] features = new float[faceQueryInfo.feature_count][];
                //for(int i = 0; i < faceQueryInfo.feature_count; ++i) {
                //    features[i] = faceQueryInfo.feature.getPointer().getFloatArray(i * faceQueryInfo.feature_size, faceQueryInfo.feature_size);
                //}
                info.setFeature(faceQueryInfo.feature.getPointer().getFloatArray(0,faceQueryInfo.feature_count * faceQueryInfo.feature_size));
                faceModelList.add(info);
                Log.e(TAG, "查询到的结果："+info.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static FaceSdk getInstance(){
        if (mFaceSdk == null){
            synchronized (FaceSdk.class){
                if (mFaceSdk == null){
                    mFaceSdk = new FaceSdk();
                }
            }
        }
        return mFaceSdk;
    }

    /**
     * 初始化方法
     * @param context
     * @param ip
     * @return
     */
    public boolean Initialize(Context context,String ip){
        if (initialized) return true;
        mContext = context;
        IntBuffer connectErrorNum = IntBuffer.allocate(1);
        mCamera = ComHaSdkLibrary.INSTANCE.HA_ConnectEx(ip, (short)9527, null, null, connectErrorNum, 0, 1);
        int errorNum = connectErrorNum.get();
        if(mCamera == null || (errorNum != 0 && errorNum != -35)) {
            Log.e(TAG, "初始化失败，Initialize, faild: errorCode: " + errorNum);
            return false;
        }

        ComHaSdkLibrary.INSTANCE.HA_RegLiveStreamCbEx(mCamera, streamDataCb, 0);
        ComHaSdkLibrary.INSTANCE.HA_RegFaceRecoCb(mCamera, faceRecoCb, Pointer.NULL);
        //注册人员查询
        ComHaSdkLibrary.INSTANCE.HA_RegFaceQueryCb(mCamera,faceQueryCb,Pointer.NULL);

        ComHaSdkLibrary.INSTANCE.HA_RegConnectEventCbEx(mCamera, connectEventCb, 0);
        initialized = true;
        return true;
    }

    /**
     * 注销
     */
    public void UnInitialize(){
        if (mCamera != null){
            ComHaSdkLibrary.INSTANCE.HA_ClearAllCallbacks(mCamera);
            ComHaSdkLibrary.INSTANCE.HA_DisConnect(mCamera);
        }
        mContext = null;
        initialized = false;
    }
    /**
     * 播放实时视频画面
     * <br>
     *     不是函数成功返回后视频画面即刻展现，可能会有网络和解码的延迟，甚至可能根本没有收到设别的视频数据
     *
     * @param holder 要播放画面的控件<br>可以不传递此参数，表示只接收数据而不展示，如果这样，就需要通过回调获取h264数据
     * @return 是否播放成功（只要设备处于连接状态且本函数为第一次被调用，则返回成功）
     */
    public boolean startVideoPlay(SurfaceHolder holder) {
        if (_played) return false;
        mDecoder = new H264Decoder(holder,mContext);
        mDecoder.play();
        _played = true;
        return true;
    }

    /**
     * 停止播放视频
     * @return
     */
    public boolean stopVideoPlay() {
        if (mDecoder != null){
            mDecoder.stopPlay();
        }
        _played = false;
        return true;
    }

    /**
     * 通过jpeg注册
     * @param id
     * @param name
     * @param role
     * @param wiegandNo
     * @param jpgData
     * @return
     */
    public int addPerson(String id,String name,int role,int wiegandNo,byte[] jpgData,int effectTime){
        int ret = -1;
        FaceFlags face = null;
        byte[] faceID = new byte[20];
        byte[] faceName = new byte[16];
        byte[] resv = new byte[8184];
        System.arraycopy(id.getBytes(),0,faceID,0,id.getBytes().length);
        System.arraycopy(name.getBytes(),0,faceName,0,name.getBytes().length);

        //Integer.MAX_VALUE
        face = new FaceFlags(faceID,faceName,role,wiegandNo,effectTime, 0,resv);
        ret = ComHaSdkLibrary.INSTANCE.HA_AddJpgFace(mCamera,face,jpgData,jpgData.length);
        return ret;
    }


    /**
     * 注册人员之前先调用haveFace检测
     * @param id
     * @param name
     * @param role
     * @param wiegandNo
     * @param imageData
     * @param twistData
     * @return
     */
    public int addPersonPacket(String id,String name,int role,int wiegandNo,byte[] imageData,byte[] twistData){
        FaceFlags face = null;
        byte[] faceID = new byte[20];
        byte[] faceName = new byte[16];
        byte[] resv = new byte[8184];
        System.arraycopy(id.getBytes(),0,faceID,0,id.getBytes().length);
        System.arraycopy(name.getBytes(),0,faceName,0,name.getBytes().length);

        face = new FaceFlags(faceID,faceName,role,wiegandNo,Integer.MAX_VALUE,0,resv);
        FaceImage faceImage = new FaceImage();
        faceImage.img_fmt = 0;
        faceImage.img_seq = 0;
        faceImage.img = new Memory(imageData.length);
        faceImage.img.write(0, imageData, 0, imageData.length);
        faceImage.img_len = imageData.length;

        FaceImage twistImage = new FaceImage();
        twistImage.img_fmt = 1;
        twistImage.img_seq = 0;
        twistImage.img = new Memory(twistData.length);
        twistImage.img.write(0, twistData, 0, twistData.length);
        twistImage.img_len = twistData.length;
        twistImage.width = 150;
        twistImage.height = 150;

        int ret = ComHaSdkLibrary.INSTANCE.HA_AddFacePacket(mCamera,face,twistImage,0,1,faceImage,1);
        return ret;
    }

    public void getPersonList(int pageNo, int role, int pageSize){
        if(faceModelList != null){
            faceModelList.clear();
        }else {
            faceModelList = new ArrayList<>();
        }
        ComHaSdkLibrary.INSTANCE.HA_QueryByRole(mCamera,role,pageNo,pageSize,(byte)1,(byte)1);
    }


    /**
     * 通过id查询 会阻塞当前线程
     * @param id
     */
    public boolean queryFaceById(final String id){
        QueryCondition condition = new QueryCondition();
        System.arraycopy(id.getBytes(),0,condition.faceID,0,id.getBytes().length);
        int ret = ComHaSdkLibrary.INSTANCE.HA_QueryFaceEx(mCamera,-1,1,100,(byte)(-1),(byte)(1),
                (short) ComHaSdkLibrary.ConditionFlag.QUERY_BY_ID,
                (short)ComHaSdkLibrary.QueryMode.QUERY_EXACT,condition);
        if (mQueryCb != null){
            if (ret != 0){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mQueryCb.onQueryById(false,null);
                    }
                });
            }

        }
        Log.e(TAG, "查询人员返回值: "+ret);
        return ret == 0;
    }

    /**
     * 修改人员信息
     * @param model
     */
    public boolean modifyFaceInfoById(QueryFaceModel model){

        byte[] faceID = new byte[20];
        byte[] faceName = new byte[16];
        byte[] resv = new byte[8184];
        System.arraycopy(model.getId().getBytes(),0,faceID,0,model.getId().getBytes().length);
        System.arraycopy(model.getName().getBytes(),0,faceName,0,model.getName().getBytes().length);
        FaceFlags faceFlags = new FaceFlags(faceID,faceName,model.getRole(),model.getWiegandNo(),
                (int)model.getEffectTime(),
                (int)model.getEffectStartTime(),resv);
        FaceImage faceImage = new FaceImage();
        faceImage.img_fmt = 0;
        faceImage.img_seq = 0;
        faceImage.img = new Memory(model.getImageData()[0].length);
        faceImage.img.write(0, model.getImageData()[0], 0, model.getImageData()[0].length);
        faceImage.img_len = model.getImageData()[0].length;
        int ret = ComHaSdkLibrary.INSTANCE.HA_ModifyFacePacket(mCamera, faceFlags, model.getFeature(), model.getFeatureSize(), model.getFeatureCount(), faceImage, 1);
        //ComHaSdkLibrary.INSTANCE.HA_FaceSyncInterface(mCamera,faceFlags,faceImage,1,1,1)
        return ret == 0;
    }
    /**
     * 开闸
     */
    public boolean openCp() {
        int result = -1;
        if (mCamera != null){
            result = ComHaSdkLibrary.INSTANCE.HA_WhiteListAlarm(mCamera, 1, 1);
            Log.e(TAG, "openCp: 开闸返回："+result);
        }
        return result == 0;
    }

    public void setMatchEnable(boolean enable) {
        ComHaSdkLibrary.INSTANCE.HA_SetMatchEnable(mCamera,enable?1:0);
    }

    public boolean getMatchEnable() {
        IntBuffer buffer = IntBuffer.allocate(4);
        ComHaSdkLibrary.INSTANCE.HA_GetMatchEnable(mCamera,buffer);
        int ret = buffer.get();
        if (ret == 0){
            return false;
        }else {
            return true;
        }
    }

    public void haveFace(byte[] imageData,HaveFaceCallBack faceCallBack){
        Object[] objects = twistImage(imageData);
        int code  = (int) objects[0];
        if (code == 0){
            byte[] faceImage = (byte[]) objects[1];
            byte[] twistImage = (byte[]) objects[2];
            faceCallBack.onFaceSuccess(faceImage,twistImage);
        }else {
            faceCallBack.onFaceFaild(code);
        }
    }




    /**
     * 如果检测到人脸且符合要求，则返回两个元素的数组，
     * 第一个元素是缩略图，
     * 第二个元素是归一化图，如果使用协议对接，
     * 则直接将其封入数据包即可（http协议的话就直接转成base64放入特定位置即可）；
     * 如果检测人脸失败或人脸不符合要求，则返回null
     * @param imageData
     * @return
     */
    private Object[] twistImage(byte[] imageData) {
        FloatBuffer feature = ByteBuffer.allocateDirect(4 * 512).asFloatBuffer();
        IntBuffer feature_size = IntBuffer.allocate(1);
        ha_rect faceRect = new ha_rect();
        ByteBuffer faceImgJpg = ByteBuffer.allocateDirect(32 * 1024);
        IntBuffer faceJpgLen = IntBuffer.allocate(1);
        int ret = ComHaSdkLibrary.INSTANCE.HA_GetJpgFaceRectAndFeature(imageData, imageData.length, feature,
                feature_size, faceRect, faceImgJpg, faceJpgLen);
        if (ret != 0)
            return new Object[] { ret };
        ByteBuffer twist_image = ByteBuffer.allocateDirect(70 * 1024);
        IntBuffer twist_size = IntBuffer.allocate(1);
        IntBuffer twist_width = IntBuffer.allocate(1);
        IntBuffer twist_height = IntBuffer.allocate(1);
        ret = ComHaSdkLibrary.INSTANCE.HA_GetJpgFaceTwist(imageData, imageData.length, twist_image, twist_size,
                twist_width, twist_height);
        if (ret != 0)
            return new Object[] { ret };
        byte[] faceImg = new byte[faceJpgLen.get()];
        byte[] twistImg = new byte[twist_size.get()];
        faceImgJpg.get(faceImg);
        twist_image.get(twistImg);
        return new Object[] { ret, faceImg, twistImg };
    }

    /**
     * 通过id删除人员
     * @param id
     * @return ‘0’，删除成功
     */
    public int delPersonById(String id){
        return ComHaSdkLibrary.INSTANCE.HA_DeleteFaceDataByPersonID(mCamera,id);
    }

    /**
     * 删除所有人员
     * @return ‘0’，删除成功
     */
    public int delAllPerson(){
        return ComHaSdkLibrary.INSTANCE.HA_DeleteFaceDataAll(mCamera);
    }

}
