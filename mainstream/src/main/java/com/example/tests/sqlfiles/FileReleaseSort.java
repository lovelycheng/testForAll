package com.example.tests.sqlfiles;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author chengtong
 * @date 2021/9/10 10:48
 */
public class FileReleaseSort {

    static String initPath = "/Users/chengtong/release_resources/3.0.4-SNAPSHOT/20211020";
    static String destPath = "/Users/chengtong/downloads/云容/上线记录/";

    static HashMap<String, List<String>> typeToPaths = new HashMap<>();
    static HashMap<String, File> pathToFile = new HashMap<>();

    static final String BPMN = "bpmn";
    static final String SQL = "sql";
    static final String ZIP = "zip";
    static final String PROPERTIES = "properties";

    static final List<String> collectedFile = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (!Files.isDirectory(Paths.get(initPath))) {
            return;
        }

        File destiny = initFileIfNotExist();

        List<String> sqlNames = getAllSqlFiles();

        FileWriter writer = new FileWriter(destiny);
        for (String fileName : sqlNames) {
            File file = pathToFile.get(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            if (s != null) {
                writer.append("------------").append(fileName).append("------------");
                writer.append("\n");
            }
            while (s != null) {
                writer.append(s);
                writer.append("\n");
                s = reader.readLine();
            }
            writer.append("------------").append(fileName).append(" end").append("------------");
            writer.append("\n");
            writer.flush();
            reader.close();
            collectedFile.add(fileName);
        }

        writer.close();
        if (collectedFile.size() != typeToPaths.get(SQL).size()) {
            System.out.println("file not read");
        }

    }

    private static File initFileIfNotExist() throws IOException {
        if (!Files.exists(Paths.get(destPath)) || !Files.isDirectory(Paths.get(destPath))) {
            Files.createDirectories(Paths.get(destPath));
        }
        File destiny = Paths.get(Paths.get(destPath) + "/newscript.sql").toFile();
        if (!Files.exists(destiny.toPath())) {
            destiny = Files.createFile(Paths.get(Paths.get(destPath) + "/newscript.sql")).toFile();
        }
        return destiny;
    }

    private static List<String> getAllSqlFiles() {
        File initFile = new File(initPath);

        deepPriority(initFile);

        List<String> sqlNames = typeToPaths.get(SQL);

        Collections.sort(sqlNames);
        return sqlNames;
    }

    static void deepPriority(File initFile) {
        File[] children = initFile.listFiles();
        if (children == null) {
            //空文件夹
            return;
        }
        for (File child : children) {
            if (child.isDirectory()) {
                deepPriority(child);
            } else {
                String suffix = getSuffix(child);
                if (SQL.equals(suffix)) {
                    addRecord(child, SQL);
                } else if (PROPERTIES.equals(suffix)) {
                    addRecord(child, PROPERTIES);
                } else if (ZIP.equals(suffix)) {
                    addRecord(child, ZIP);
                } else if (BPMN.equals(suffix)) {
                    addRecord(child, BPMN);
                }
            }
        }
    }

    private static String getSuffix(File child) {
        String[] arr = child.getAbsolutePath().split("\\.");
        return arr[arr.length - 1];
    }

    private static void addRecord(File child, String properties) {
        List<String> list = typeToPaths.computeIfAbsent(properties, k -> new ArrayList<>());
        list.add(child.getAbsolutePath());
        pathToFile.put(child.getAbsolutePath(), child);
    }


}
