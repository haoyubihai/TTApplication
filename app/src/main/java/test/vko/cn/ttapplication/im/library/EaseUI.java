package test.vko.cn.ttapplication.im.library;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMMessage;

import java.util.Iterator;
import java.util.List;

import test.vko.cn.ttapplication.im.library.model.EaseUser;

/**
 * Created by JiaRH on 2015/11/13.
 */
public class EaseUI {
    private static EaseUI instance = null;
    private boolean sdkInited;
    private Context appContext;
    private EaseUI(){}
    private String TAG = EaseUI.class.getSimpleName();
    EaseUserProfileProvider userProvider;
    EaseSettingsProvider settingsProvider;

    /**
     * 获取EaseUI单实例对象
     * @return
     */
    public synchronized static EaseUI getInstance(){
        if(instance == null){
            instance = new EaseUI();
        }
        return instance;
    }

    /**
     *this function will initialize the HuanXin SDK
     *
     * @return boolean true if caller can continue to call HuanXin related APIs after calling onInit, otherwise false.
     *
     * 初始化环信sdk及easeui库
     * 返回true如果正确初始化，否则false，如果返回为false，请在后续的调用中不要调用任何和环信相关的代码
     * @param context
     * @return
     */
    public synchronized boolean init(Context context){

        if(sdkInited){
            return true;
        }
        appContext = context;

        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);

        Log.d(TAG, "process app name : " + processAppName);

        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(appContext.getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return false;
        }
        // 初始化环信SDK,一定要先调用init()
        EMChat.getInstance().init(context);

//        initChatOptions();
//        if(settingsProvider == null){
//            settingsProvider = new DefaultSettingsProvider();
//        }

        sdkInited = true;
        return true;
    }
    /**
     * check the application process name if process name is not qualified, then we think it is a service process and we will not init SDK
     * @param pID
     * @return
     */
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = appContext.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }

    /**
     * 用户属性提供者
     * @author wei
     *
     */
    public static interface EaseUserProfileProvider {
        /**
         * 返回此username对应的user
         * @param username 环信id
         * @return
         */
        EaseUser getUser(String username);
    }
    /**
     * 设置用户属性提供者
     * @param userProvider
     */
    public void setUserProfileProvider(EaseUserProfileProvider userProvider){
        this.userProvider = userProvider;
    }

    /**
     * 获取用户属性提供者
     * @return
     */
    public EaseUserProfileProvider getUserProfileProvider(){
        return userProvider;
    }

    public void setSettingsProvider(EaseSettingsProvider settingsProvider){
        this.settingsProvider = settingsProvider;
    }

    public EaseSettingsProvider getSettingsProvider(){
        return settingsProvider;
    }
    /**
     * 新消息提示设置的提供者
     *
     */
    public static interface EaseSettingsProvider {
        boolean isMsgNotifyAllowed(EMMessage message);
        boolean isMsgSoundAllowed(EMMessage message);
        boolean isMsgVibrateAllowed(EMMessage message);
        boolean isSpeakerOpened();
    }
}
