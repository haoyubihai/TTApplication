package test.vko.cn.ttapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JiaRH on 2015/10/15.
 */
public class Person implements Parcelable {


    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    private int code;
    private String msg;
    private long stime;
    private List<DataEntity> data;

    public Person() {
    }

    protected Person(Parcel in) {
        this.code = in.readInt();
        this.msg = in.readString();
        this.stime = in.readLong();
        this.data = new ArrayList<DataEntity>();
        in.readList(this.data, List.class.getClassLoader());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getStime() {
        return stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.msg);
        dest.writeLong(this.stime);
        dest.writeList(this.data);
    }

    public static class DataEntity {


        private int secRate;
        private String goodsId;
        private String sectionId;
        private String bookId;
        private String bookName;
        private List<VideoEntity> video;

        public int getSecRate() {
            return secRate;
        }

        public void setSecRate(int secRate) {
            this.secRate = secRate;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getSectionId() {
            return sectionId;
        }

        public void setSectionId(String sectionId) {
            this.sectionId = sectionId;
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public List<VideoEntity> getVideo() {
            return video;
        }

        public void setVideo(List<VideoEntity> video) {
            this.video = video;
        }

        public static class VideoEntity {
            /**
             * teacherUrl : http://cdn.vkoimg.cn/upload/pic/user/face/13482107110030057_middle.jpg
             * duration : 116
             * school :
             * trackRate : 0
             * videoUrl : http://cdn.vkoimg.cn/content/video/10607271bg.jpg
             * videoRate : 0
             * tVideo : http://static.vko.cn/content/teacher/video/T139.mp4
             * videoId : 10607271
             * track : 0
             * videoName : 构成空间几何体的基本元素
             * teacherName : 王安平
             * teacherId : 13482107110030057
             */

            private String teacherUrl;
            private int duration;
            private String school;
            private int trackRate;
            private String videoUrl;
            private int videoRate;
            private String tVideo;
            private String videoId;
            private int track;
            private String videoName;
            private String teacherName;
            private String teacherId;

            public String getTeacherUrl() {
                return teacherUrl;
            }

            public void setTeacherUrl(String teacherUrl) {
                this.teacherUrl = teacherUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public int getTrackRate() {
                return trackRate;
            }

            public void setTrackRate(int trackRate) {
                this.trackRate = trackRate;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public int getVideoRate() {
                return videoRate;
            }

            public void setVideoRate(int videoRate) {
                this.videoRate = videoRate;
            }

            public String getTVideo() {
                return tVideo;
            }

            public void setTVideo(String tVideo) {
                this.tVideo = tVideo;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public int getTrack() {
                return track;
            }

            public void setTrack(int track) {
                this.track = track;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public String getTeacherId() {
                return teacherId;
            }

            public void setTeacherId(String teacherId) {
                this.teacherId = teacherId;
            }
        }
    }
}
