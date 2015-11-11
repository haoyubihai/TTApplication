package test.vko.cn.ttapplication.presenter;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.easemob.chat.EMChat;

import java.util.Iterator;
import java.util.List;

/**
 * Created by JiaRH on 2015/11/10.
 */
public class HuanXinHelper {
    private String TAG = HuanXinHelper.class.getSimpleName();
    private Context mContext;
    private static HuanXinHelper ourInstance = new HuanXinHelper();

    public static HuanXinHelper getInstance() {
        return ourInstance;
    }

    private HuanXinHelper() {
    }

    public void init(Context context) {
        this.mContext = context;
        if (initAppData(context)) {
            EMChat.getInstance().setDebugMode(true);
        }
    }

    private boolean initAppData(Context context) {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);

        Log.d(TAG, "process app name : " + processAppName);

        // 如果app启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return false;
        }
        // 初始化环信SDK,一定要先调用init()
        EMChat.getInstance().init(context);
        return true;
    }

    /**
     * check the application process name if process name is not qualified, then we think it is a service process and we will not init SDK
     *
     * @param pID
     * @return
     */
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = mContext.getPackageManager();
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
}
