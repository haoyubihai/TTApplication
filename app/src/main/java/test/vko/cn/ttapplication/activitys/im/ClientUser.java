package test.vko.cn.ttapplication.activitys.im;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JiaRH on 2015/10/27.
 */
public class ClientUser implements Parcelable {

    public static final Parcelable.Creator<ClientUser> CREATOR = new Parcelable.Creator<ClientUser>() {
        public ClientUser createFromParcel(Parcel source) {
            return new ClientUser(source);
        }

        public ClientUser[] newArray(int size) {
            return new ClientUser[size];
        }
    };
    private String name;
    private String appId;
    private String appAcount;
    private String voipAcount;
    private String password;

    public ClientUser() {
    }

    protected ClientUser(Parcel in) {
        this.name = in.readString();
        this.appId = in.readString();
        this.appAcount = in.readString();
        this.voipAcount = in.readString();
        this.password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.appId);
        dest.writeString(this.appAcount);
        dest.writeString(this.voipAcount);
        dest.writeString(this.password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppAcount() {
        return appAcount;
    }

    public void setAppAcount(String appAcount) {
        this.appAcount = appAcount;
    }

    public String getVoipAcount() {
        return voipAcount;
    }

    public void setVoipAcount(String voipAcount) {
        this.voipAcount = voipAcount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
