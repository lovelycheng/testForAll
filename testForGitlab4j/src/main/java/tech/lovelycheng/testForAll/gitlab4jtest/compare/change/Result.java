package tech.lovelycheng.testForAll.gitlab4jtest.compare.change;

import java.io.File;

/**
 * @author chengtong
 * @date 2022/4/7 15:26
 */
public class Result {

    public String fileName;
    public boolean deleted;
    public boolean newAdded;
    public boolean fileRenamed;



    public static class Block{

        String fileName;
        int startNumber;
        int endNumber;



    }

}
