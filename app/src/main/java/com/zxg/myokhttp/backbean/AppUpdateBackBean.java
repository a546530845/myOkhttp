package com.zxg.myokhttp.backbean;


import com.zxg.myokhttp.base.BackBaseBean;

/**
 * 作者：zxg on 2016/9/30 14:59
 * app更新请求返回bean
 * 看看两个app是否有要更新的
 */
public class AppUpdateBackBean extends BackBaseBean {

    /**
     * id : 1
     * versionType : 2
     * versionName : 2
     * versionCode : android1
     * increase :
     * versionDesc : 1
     * creator : null
     * createTime : 1445645445546654
     * updateTime : 465456444646464
     * fileId : 405
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AppUpdateBackBean{" +
                "data=" + data +
                "} " + super.toString();
    }

    public static class DataBean {
        private int id;
        private int versionType;
        private String versionName;
        private String versionCode;
        private String increase;
        private String versionDesc;
        private String creator;
        private long createTime;
        private long updateTime;
        private int fileId;

        @Override
        public String toString() {
            return "DataBean{" +
                    "createTime=" + createTime +
                    ", id=" + id +
                    ", versionType=" + versionType +
                    ", versionName='" + versionName + '\'' +
                    ", versionCode='" + versionCode + '\'' +
                    ", increase='" + increase + '\'' +
                    ", versionDesc='" + versionDesc + '\'' +
                    ", creator='" + creator + '\'' +
                    ", updateTime=" + updateTime +
                    ", fileId=" + fileId +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersionType() {
            return versionType;
        }

        public void setVersionType(int versionType) {
            this.versionType = versionType;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getIncrease() {
            return increase;
        }

        public void setIncrease(String increase) {
            this.increase = increase;
        }

        public String getVersionDesc() {
            return versionDesc;
        }

        public void setVersionDesc(String versionDesc) {
            this.versionDesc = versionDesc;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getFileId() {
            return fileId;
        }

        public void setFileId(int fileId) {
            this.fileId = fileId;
        }
    }
}
