package tech.lovelycheng.demo.test.fileimport.easyimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.Archived;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmLoan;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmLoanRate;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmOpen;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmRepay;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmRepayItem;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.XmTermStatus;

/**
 * @author chengtong
 * @date 2022/6/28 15:22
 */
@Slf4j
public class ImportFromDict {

    final static String PATH = "/Users/chengtong/Downloads/小米助贷+联贷DEMO文件/小米助贷 测试/DES加密贷后文件demo（带总账）/解密/";
    final static String DEST_PATH = "/Users/chengtong/Downloads/测试/";

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

        FileInputStream file = new FileInputStream(
            "/Users/chengtong/demo/testForFileImport/src/main/resources/post_loan_scenario.xlsx");

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int sheetNum = workbook.getNumberOfSheets();
        for(int i =1;i<sheetNum;i++){
            XSSFSheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName().trim();
            log.info("sheetName:{}",sheetName);
            Map<String, Method> stringGetMethodMap = configuration.getClassGetMethodsByFileName(sheetName);
            if(stringGetMethodMap == null){
                log.error("skip sheet:{}",sheetName);
                continue;
            }

            List<Archived> xmLoanList = dataMap.get(sheetName).stream()
                .sorted(Comparator.comparing(Archived::getCurDate))
                .collect(Collectors.toList());
            int rowNum = 2;
            for (Archived xmLoan : xmLoanList) {
                XSSFRow row = sheet.createRow(rowNum);
                int cellNum = 0;
                XSSFRow xssfRow = sheet.getRow(1);
                Iterator<Cell> cellIterator = xssfRow.cellIterator();
                while (cellIterator.hasNext()) {
                    log.debug("cellNum:{},rowNum:{}", cellNum, rowNum);
                    Cell cell = cellIterator.next();
                    String column = cell.getStringCellValue().trim();
                    Method getMethod = stringGetMethodMap.get(column);
                    if (getMethod == null) {
                        log.error("column:{}", column);
                        cellNum++;
                        continue;
                    }
                    XSSFCell xssfCell = row.createCell(cellNum, CellType.STRING);
                    xssfCell.setCellValue(String.valueOf(getMethod.invoke(xmLoan)));
                    cellNum++;
                }
                rowNum++;
            }
        }

        file.close();
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(
                new File("/Users/chengtong/demo/testForFileImport/src/main/resources/post_loan_scenario.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
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
        Map<String, Method> stringMethodMap = configuration.getClassSetMethodsByFileName(subName);
        List<String> stringList = FileUtil.readUtf8Lines(dataFile);
        String[] header = stringList.get(0)
            .split(",");

        for (int i = 1; i < stringList.size(); i++) {
            String[] data = stringList.get(i)
                .split(",");
            Archived de = c.newInstance();

            for (int j = 0; j < header.length; j++) {
                log.info("header:{}", header[j]);
                Method method = stringMethodMap.get(header[j]);
                if(method == null){
                    log.error("文件:{},缺失字段:{}",dataFile.getName(),header[j]);
                    continue;
                }
                method.invoke(de, data[j]);
            }
            dataMap.computeIfAbsent(subName, k -> new ArrayList<>());
            List<Archived> archivedList = dataMap.get(subName);
            archivedList.add(de);
        }
    }

}
