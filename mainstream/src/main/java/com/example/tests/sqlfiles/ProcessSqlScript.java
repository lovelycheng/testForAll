package com.example.tests.sqlfiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.example.tests.sqlfiles.FileReleaseSort.*;


/**
 * @author chengtong
 * @date 2021/9/11 17:06
 */
public class ProcessSqlScript {

    static final String PREFIX_DECLARE = "declare";
    static final String PREFIX_CREATE_TABLE = "create table";
    static final String PREFIX_TABLE_SPACE = "TABLESPACE";
    static final String PREFIX_COMMENT = "comment on";
    static final String PREFIX_ALTER = "alter table";

    static final String createTableRegax = "^create table credit|";

    /**
     * 库-表-sql 相关
     */
    static HashMap<String, List<TableSql>> tableNameToSQLs = new HashMap<>();
    static HashMap<String, List<String>> databaseToTables = new HashMap<>();
    /**
     * 文件类型相关的存储
     */
    static HashMap<String, List<String>> typeToPaths = new HashMap<>();
    static HashMap<String, File> pathToFile = new HashMap<>();

    static HashMap<String, FileWriter> dbToWriter = new HashMap<>();

    static SQLDatabaseType[] dbTypeList = SQLDatabaseType.values();


    static String initPath = "/Users/chengtong/release_resources/上线记录";
    static String destPath = "/Users/chengtong/downloads/云容/上线记录/";

    public static void main(String[] args) throws IOException {
        if (!Files.isDirectory(Paths.get(initPath))) {
            return;
        }
        Arrays.stream(dbTypeList).forEach(s -> {
            try {
                initSqlFileAndWriterMap(s.name());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<String> sqlNames = getAllSqlFiles();

        for (String fileName : sqlNames) {
            File file = pathToFile.get(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            TableSql tableSql = null;
            while (s != null) {
                if (s.startsWith(PREFIX_DECLARE)) {
                    if(tableSql == null){
                        tableSql = new TableSql();
                    }
                    DeclareContext context=fillTableSqlDeclareContext(s, tableSql);
                    s = reader.readLine();
                    while (!s.startsWith("/")){
                        context.lines.add(s);
                    }
                    context.lines.add(s);
                    context.isActive=false;
                } else if (s.startsWith(PREFIX_CREATE_TABLE)) {
                    if(tableSql == null){
                        tableSql = new TableSql();
                    }


                } else if (s.startsWith(PREFIX_TABLE_SPACE)) {

                } else if (s.startsWith(PREFIX_COMMENT)) {

                } else if (s.startsWith(PREFIX_ALTER)) {

                } else {
                    DeclareContext context = tableSql.getDeclareContexts().get(0);
                    if(context != null && context.isActive)context.lines.add(s);

                }
                s = reader.readLine();
            }

            reader.close();
        }

//        writer.close();

    }

    private static DeclareContext fillTableSqlDeclareContext(String s, TableSql tableSql) {
        DeclareContext declareContext = new DeclareContext();
        declareContext.isActive=true;
        declareContext.lines.add(s);
        if(tableSql.getDeclareContexts() == null){
            List<DeclareContext> list = new ArrayList<>();
            tableSql.setDeclareContexts(list);
        }
        tableSql.getDeclareContexts().add(declareContext);
        return declareContext;
    }

    private static void initSqlFileAndWriterMap(String type) throws IOException {
        String fileName = type + "." + SQL;
        File destiny = Paths.get(Paths.get(destPath) + fileName).toFile();
        if (!Files.exists(destiny.toPath())) {
            Files.createFile(Paths.get(Paths.get(destPath) + fileName)).toFile();
        }
        FileWriter creditWriter = new FileWriter(destPath + fileName);
        dbToWriter.put(type, creditWriter);
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
