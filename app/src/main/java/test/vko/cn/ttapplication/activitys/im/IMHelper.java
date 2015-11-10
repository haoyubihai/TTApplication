package test.vko.cn.ttapplication.activitys.im;

import android.content.Context;

import com.yuntongxun.ecsdk.ECDevice;
import com.yuntongxun.ecsdk.ECInitParams;

/**
 * Created by JiaRH on 2015/10/27.
 */
public class IMHelper {
    public static IMHelper INSTANCE;
    private ECInitParams params;

    private IMHelper() {
    }

    public static IMHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new IMHelper();
        }
        return INSTANCE;
    }

    public static void init(Context context) {
        //  第一步：初始化SDK
// 判断SDK是否已经初始化，如果已经初始化则可以直接调用登陆接口
// 没有初始化则先进行初始化SDK，然后调用登录接口注册SDK
        if (!ECDevice.isInitialized()) {
            ECDevice.initial(context, new ECDevice.InitListener() {
                @Override
                public void onInitialized() {
                    // SDK已经初始化成功
                }

                @Override
                public void onError(Exception exception) {
                    // SDK 初始化失败,可能有如下原因造成
                    // 1、可能SDK已经处于初始化状态
                    // 2、SDK所声明必要的权限未在清单文件（AndroidManifest.xml）里配置、
                    //    或者未配置服务属性android:exported="false";
                    // 3、当前手机设备系统版本低于ECSDK所支持的最低版本（当前ECSDK支持
                    //    Android Build.VERSION.SDK_INT 以及以上版本）
                }
            });
        }
    }

    private void initParams() {
        ClientUser user = TTAPPManger.getUser();
        if (params == null || params.getInitParams() == null || params.getInitParams().isEmpty()) {
            params = ECInitParams.createParams();
        }
        params.reset();
        params.setUserid(user.getVoipAcount());
        params.setPwd(user.getPassword());

    }
}
