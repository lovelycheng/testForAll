package tech.lovelycheng.demo.test.fileimport.easyimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.base.Joiner;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.Archived;

/**
 * @author chengtong
 * @date 2022/6/28 15:22
 */
@Slf4j
public class ImportFromDictAndExportToZd {

    final static String PATH = "/Users/chengtong/demo/testForFileImport/src/main/resources/mockdata/artifact/";

    private static Map<String, Map<String, Method>> classToMethod = new HashMap<>();

    public static void main(String[] args) throws Exception {

        ImportConfiguration configuration = new ImportConfiguration();

        File dict = new File(PATH);
        File[] list = dict.listFiles();//日期

        assert list != null;

        Map<String, List<Archived>> dataMap = new HashMap<>();

        for (File file : list) {
            if (!file.isDirectory()) {
                continue;
            }
            File[] dateFiles = file.listFiles();
            if (dateFiles == null) {
                continue;
            }
            for (File dataFile : dateFiles) {
                processOneFile(configuration, dataMap, dataFile);
            }
        }

    }

    private static void processOneFile(ImportConfiguration configuration, Map<String, List<Archived>> dataMap,
        File dataFile) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        String name = dataFile.getName();
        if (name.endsWith(".xml")) {
            return;
        }
        log.info("fileName:{}", dataFile.getName());
        String subName = name.split("\\.")[0].toLowerCase();
        Class<? extends Archived> c = configuration.getClassByFileName(subName);
        if (c == null) {
            return;
        }
        List<String> stringList = FileUtil.readUtf8Lines(dataFile);
        List<String> toWrites = new ArrayList<>();
        if (stringList.isEmpty()) {
            return;
        }
        String[] header = stringList.get(0)
            .split(",");
        toWrites.add(String.join(",", header));
        for (int i = 1; i < stringList.size(); i++) {
            String[] data = stringList.get(i)
                .split(",");
            for (int j = 0; j < header.length; j++) {
                log.info("header:{}", header[j]);
                if (header[j].equals("loan_id") && !data[j].startsWith("at")) {
                    data[j] = "at" + data[j];
                }
                if (header[j].equals("seq_no")) {
                    data[j] = "at" + data[j];
                }
                if (header[j].equals("cert_no")) {
                    header[j] = "330122199106202817";
                }
            }
            toWrites.add(String.join(",", data));
        }
        toWrites.add("");
        FileUtil.writeLines(toWrites, dataFile, StandardCharsets.UTF_8);
    }
}
