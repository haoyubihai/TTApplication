package test.vko.cn.ttapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JiaRH on 2015/11/2.
 */
public class RefreshData implements Parcelable {
    public static final Parcelable.Creator<RefreshData> CREATOR = new Parcelable.Creator<RefreshData>() {
        public RefreshData createFromParcel(Parcel source) {
            return new RefreshData(source);
        }

        public RefreshData[] newArray(int size) {
            return new RefreshData[size];
        }
    };
    private String ss;

    public RefreshData() {
    }

    protected RefreshData(Parcel in) {
        this.ss = in.readString();
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ss);
    }
}
