package com.android.datadisplay.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class FileBean implements Parcelable {
    @TreeNodeId
    private int _id;
    @TreeNodePid
    private int parentId;
    @TreeNodeLabel
    private String name;
    private long length;
    private String desc;
    @TreeNodeBaseBean
    private BaseBean baseBean;

    private int mData;
    private FileBean(Parcel in){
        mData = in.readInt();
    }
    public FileBean(int _id, int parentId, String name)
    {
        super();
        this._id = _id;
        this.parentId = parentId;
        this.name = name;
    }
    public FileBean(int _id, int parentId, String name,BaseBean baseBean)
    {
        super();
        this._id = _id;
        this.parentId = parentId;
        this.name = name;
        this.baseBean = baseBean;
    }


    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<FileBean> CREATOR = new Creator<FileBean>() {
        @Override
        public FileBean createFromParcel(Parcel source) {
            return new FileBean(source);
        }

        @Override
        public FileBean[] newArray(int size) {
            return new FileBean[size];
        }
    };
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mData);
    }

}
