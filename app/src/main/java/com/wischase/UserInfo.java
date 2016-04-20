package com.wischase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sri_suki on 3/22/2016.
 */
public class UserInfo implements Parcelable {

    // User Id and User Name
    private long userId;
    private String userName;

    public UserInfo() {}
/**
 * Constructor for a User object
 * @param userId
 * @param userName
 */

public void getuserInfo(long userId, String userName) {

    this.userId =userId;
    this.userName = userName;

}

    /* Parcelable interface implementation*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel userInfoParcel, int flags) {
        userInfoParcel.writeLong(this.userId);
        userInfoParcel.writeString(this.userName);

    }

    public UserInfo(Parcel uIParcel) {
        this.userId = uIParcel.readInt();
        this.userName = uIParcel.readString();
    }
    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
    /* Parcelable interface implementation*/

}
