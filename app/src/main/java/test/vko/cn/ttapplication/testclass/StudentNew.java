package test.vko.cn.ttapplication.testclass;

/**
 * Created by JiaRH on 2015/11/5.
 */
public class StudentNew {

    private String name;//require
    private String sex;//require
    private String age;
    private String grade;
    private String address;

    private String tel;
    private String like;

    public StudentNew(Builder builder) {
        this.name = builder.name;
        this.sex = builder.sex;
        this.age = builder.age;
        this.grade = builder.grade;
        this.address = builder.address;
        this.tel = builder.tel;
        this.like = builder.like;
    }

    public static class Builder {
        private String name;//require
        private String sex;//require
        private String age;
        private String grade;
        private String address;

        private String tel;
        private String like;

        public Builder(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder tel(String tel) {
            this.tel = tel;
            return this;
        }

        public Builder like(String like) {
            this.like = like;
            return this;
        }

        public StudentNew build() {
            return new StudentNew(this);
        }
    }
}
