package test.vko.cn.ttapplication.testclass;

/**
 * Created by JiaRH on 2015/11/5.
 */
public class Student {
    private String name;//require
    private String sex;//require
    private String age;
    private String grade;
    private String address;

    private String tel;
    private String like;

    /**
     * 错误的调用 参数顺序错了
     *
     * @param name
     * @param sex
     */
    public Student(String name, String sex) {
        this(name, sex, null);
    }

    /**
     * 正确
     *
     * @param sex
     * @param age
     * @param name
     */
    public Student(String sex, String age, String name) {
        this(name, null, age, sex);
    }

    public Student(String name, String grade, String age, String sex) {
        this.name = name;
        this.grade = grade;
        this.age = age;
        this.sex = sex;
    }

    public Student(String name, String sex, String age, String grade, String address) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
        this.address = address;
    }

    public Student(String name, String sex, String age, String grade, String address, String tel) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
        this.address = address;
        this.tel = tel;
    }

    public Student(String name, String like, String tel, String address, String grade, String age, String sex) {
        this.name = name;
        this.like = like;
        this.tel = tel;
        this.address = address;
        this.grade = grade;
        this.age = age;
        this.sex = sex;
    }


}
