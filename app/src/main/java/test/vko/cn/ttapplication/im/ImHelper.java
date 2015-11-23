package test.vko.cn.ttapplication.im;

import android.content.Context;

import com.easemob.chat.EMChat;

import test.vko.cn.ttapplication.im.library.EaseUI;
import test.vko.cn.ttapplication.im.library.model.DemoModel;

public class ImHelper {
    /**
     * 数据同步listener
     */
    static public interface DataSyncListener {
        /**
         * 同步完毕
         * @param success true：成功同步到数据，false失败
         */
         void onSyncComplete(boolean success);
    }
    private static ImHelper instance = null;
    private Context appContext;
    private EaseUI easeUI;
    private DemoModel demoModel = null;
    private String username;
    private ImHelper() {
    }

    public synchronized static ImHelper getInstance() {
        if (instance == null) {
            instance = new ImHelper();
        }
        return instance;
    }

    /**
     * sdk 初始化操作
     * @param context
     */
    public void init(Context context) {
        if (EaseUI.getInstance().init(context)){
            this.appContext = context;
            EMChat.getInstance().setDebugMode(true);
            easeUI = EaseUI.getInstance();
            demoModel = new DemoModel(context);
//            setEaseUIProviders();
        }

    }


//    private EaseUser getUserInfo(String username){
//        //获取user信息，demo是从内存的好友列表里获取，
//        //实际开发中，可能还需要从服务器获取用户信息,
//        //从服务器获取的数据，最好缓存起来，避免频繁的网络请求
//        EaseUser user = null;
//        if(username.equals(EMChatManager.getInstance().getCurrentUser()))
//            return getUserProfileManager().getCurrentUserInfo();
//        user = getContactList().get(username);
//        //TODO 获取不在好友列表里的群成员具体信息，即陌生人信息，demo未实现
//        if(user == null && getRobotList() != null){
//            user = getRobotList().get(username);
//        }
//        return user;
//    }
    /**
     * 获取当前用户的环信id
     */
    public String getCurrentUsernName(){
        if(username == null){
            username = demoModel.getCurrentUsernName();
        }
        return username;
    }

//    protected void setEaseUIProviders() {
//        //需要easeui库显示用户头像和昵称设置此provider
//        easeUI.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
//
//            @Override
//            public EaseUser getUser(String username) {
//                return getUserInfo(username);
//            }
//        });
//
//        //不设置，则使用easeui默认的
//        easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() {
//
//            @Override
//            public boolean isSpeakerOpened() {
//                return demoModel.getSettingMsgSpeaker();
//            }
//
//            @Override
//            public boolean isMsgVibrateAllowed(EMMessage message) {
//                return demoModel.getSettingMsgVibrate();
//            }
//
//            @Override
//            public boolean isMsgSoundAllowed(EMMessage message) {
//                return demoModel.getSettingMsgSound();
//            }
//
//            @Override
//            public boolean isMsgNotifyAllowed(EMMessage message) {
//                if(message == null){
//                    return demoModel.getSettingMsgNotification();
//                }
//                if(!demoModel.getSettingMsgNotification()){
//                    return false;
//                }else{
//                    //如果允许新消息提示
//                    //屏蔽的用户和群组不提示用户
//                    String chatUsename = null;
//                    List<String> notNotifyIds = null;
//                    // 获取设置的不提示新消息的用户或者群组ids
//                    if (message.getChatType() == EMMessage.ChatType.Chat) {
//                        chatUsename = message.getFrom();
//                        notNotifyIds = demoModel.getDisabledIds();
//                    } else {
//                        chatUsename = message.getTo();
//                        notNotifyIds = demoModel.getDisabledGroups();
//                    }
//
//                    if (notNotifyIds == null || !notNotifyIds.contains(chatUsename)) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        });
//        //不设置，则使用easeui默认的
//        easeUI.getNotifier().setNotificationInfoProvider(new EaseNotificationInfoProvider() {
//
//            @Override
//            public String getTitle(EMMessage message) {
//                //修改标题,这里使用默认
//                return null;
//            }
//
//            @Override
//            public int getSmallIcon(EMMessage message) {
//                //设置小图标，这里为默认
//                return 0;
//            }
//
//            @Override
//            public String getDisplayedText(EMMessage message) {
//                // 设置状态栏的消息提示，可以根据message的类型做相应提示
//                String ticker = EaseCommonUtils.getMessageDigest(message, appContext);
//                if(message.getType() == Type.TXT){
//                    ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
//                }
//                EaseUser user = getUserInfo(message.getFrom());
//                if(user != null){
//                    return getUserInfo(message.getFrom()).getNick() + ": " + ticker;
//                }else{
//                    return message.getFrom() + ": " + ticker;
//                }
//            }
//
//            @Override
//            public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
//                return null;
//                // return fromUsersNum + "个基友，发来了" + messageNum + "条消息";
//            }
//
//            @Override
//            public Intent getLaunchIntent(EMMessage message) {
//                //设置点击通知栏跳转事件
//                Intent intent = new Intent(appContext, ChatActivity.class);
//                //有电话时优先跳转到通话页面
//                if(isVideoCalling){
//                    intent = new Intent(appContext, VideoCallActivity.class);
//                }else if(isVoiceCalling){
//                    intent = new Intent(appContext, VoiceCallActivity.class);
//                }else{
//                    ChatType chatType = message.getChatType();
//                    if (chatType == ChatType.Chat) { // 单聊信息
//                        intent.putExtra("userId", message.getFrom());
//                        intent.putExtra("chatType", Constant.CHATTYPE_SINGLE);
//                    } else { // 群聊信息
//                        // message.getTo()为群聊id
//                        intent.putExtra("userId", message.getTo());
//                        if(chatType == ChatType.GroupChat){
//                            intent.putExtra("chatType", Constant.CHATTYPE_GROUP);
//                        }else{
//                            intent.putExtra("chatType", Constant.CHATTYPE_CHATROOM);
//                        }
//
//                    }
//                }
//                return intent;
//            }
//        });
//    }

}