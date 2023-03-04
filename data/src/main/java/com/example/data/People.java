package com.example.data;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author chengtong
 * @date 2023/2/13 17:56
 */
public class People {

    private String id;
    private String name;
    private String mobile;

    public People(String id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof People))
            return false;
        People people = (People) o;
        return Objects.equals(id, people.id) && Objects.equals(name, people.name) && Objects.equals(mobile,
            people.mobile);
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(index, name, mobile);
    // }

    public static void main(String[] args) {
        People a = new People("1","1","1");
        HashMap s = new HashMap();
        s.put(a,"asdasd");
        s.put("asdasd",a);

        People a1 = new People("1","1","1");
        System.out.println(s.get(a1));
        System.out.println(s.get("asdasd"));

        PeopleB b = new PeopleB("1","1","1");
        s.put(b,"asdasd");
        s.put("dsadsa",b);

        PeopleB b1 = new PeopleB("1","1","1");
        System.out.println(s.get(b1));
        System.out.println(s.get("dsadsa"));


    }

    private static class PeopleB{
        private String id;
        private String name;
        private String mobile;

        public PeopleB(String id, String name, String mobile) {
            this.id = id;
            this.name = name;
            this.mobile = mobile;
        }

        // @Override
        // public boolean equals(Object o) {
        //     if (this == o)
        //         return true;
        //     if (!(o instanceof People))
        //         return false;
        //     People people = (People) o;
        //     return Objects.equals(index, people.index) && Objects.equals(name, people.name) && Objects.equals(mobile,
        //         people.mobile);
        // }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, mobile);
        }
    }

}
