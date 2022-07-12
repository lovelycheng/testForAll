package tech.lovelycheng.demo.test.filecomm.easyfind;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.harmony.unpack200.bytecode.CPRef;

import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * @author chengtong
 * @date 2022/7/11 21:22
 */
public class EasyFind {

    private static String fileDir1 = "/Users/chengtong/admin/";
    private static String fileDir2 = "/Users/chengtong/admin-query/";

    public static void main(String[] args) {

        Map<String, File> fileMap = new HashMap<>();
        File file1 = new File(fileDir1);
        fillMap1(fileMap, file1, fileDir1);

        Map<String, File> fileMap2 = new HashMap<>();
        File file2 = new File(fileDir2);
        fillMap1(fileMap2, file2, fileDir2);

        StringBuilder stringBuilder = new StringBuilder();
        for (String path : fileMap.keySet()) {
            if (fileMap2.containsKey(path)) {
                stringBuilder.append(path);
                stringBuilder.append("  ");
                stringBuilder.append(fileMap2.get(path)
                    .getName());

                if (!SecureUtil.md5(fileMap2.get(path))
                    .equals(SecureUtil.md5(fileMap.get(path)))){
                    stringBuilder.append(" MD5 not equal ");
                }
                    stringBuilder.append("\n");
            }
        }
        FileUtil.writeBytes(stringBuilder.toString()
            .getBytes(StandardCharsets.UTF_8), new File("/Users/chengtong/Documents/admin&admin-query梳理/commFile.log"));

    }

    private static void fillMap1(Map<String, File> fileMap, File file1, String perfix) {
        File[] fileNames = file1.listFiles();
        if (fileNames == null) {
            return;
        }
        for (File file : fileNames) {
            if (file.getName()
                .equals("target") || file.getName()
                .equals("logs") || file.getName()
                .startsWith(".")) {
                continue;
            }
            if (!file.isDirectory()) {
                fileMap.put(file.getAbsolutePath()
                    .substring(perfix.length()), file);
            } else {
                fillMap1(fileMap, file, perfix);
            }
        }
    }

}
