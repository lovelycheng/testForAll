package tech.lovelycheng.demo.test.filewash;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import cn.binarywang.tools.generator.ChineseAddressGenerator;
import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import cn.binarywang.tools.generator.base.GenericGenerator;
import cn.hutool.core.io.FileUtil;

/**
 * @author chengtong
 * @date 2022/7/14 19:03
 */
public class WashData {

    public static String XMLD_FILE
        = "/Users/chengtong/demo/testForFileImport/src/main/resources/mockdata/creditData/xmxd_cust_cred_20220710.txt";


    public static String XMZD_FILE
        = "/Users/chengtong/demo/testForFileImport/src/main/resources/mockdata/creditData/xmxdzd_cust_cred_20220710.txt";
    public static String XMZD_OPEN_FILE
        = "/Users/chengtong/demo/testForFileImport/src/main/resources/mockdata/creditData/xmxdzd_open_20220710.txt";

    public static String dest
        = "/Users/chengtong/demo/testForFileImport/src/main/resources/mockdata/creditData/washed/";

    public static Map<String, String> ID_HASH_MAP = new HashMap<>();
    public static Map<String, String> NAME_HASH_MAP = new HashMap<>();
    public static Map<String, String> MOBILE_HASH_MAP = new HashMap<>();

    static GenericGenerator idGen = ChineseIDCardNumberGenerator.getInstance();
    static GenericGenerator nameGen = ChineseNameGenerator.getInstance();
    static GenericGenerator mobileGen = ChineseMobileNumberGenerator.getInstance();
    static Long cardNo = 6222399912228888L;
    static GenericGenerator addressGen = ChineseAddressGenerator.getInstance();

    public static void main(String[] args) {
        washCreditFile(XMZD_FILE, "xmxdzd_cust_cred_20201101.txt");
        washCreditFile(XMLD_FILE, "xmxd_cust_cred_20201101.txt");
        washOpen();
    }

    private static void washOpen() {
        List<String> openLists = FileUtil.readLines(XMZD_OPEN_FILE, StandardCharsets.UTF_8);
        List<String> washed = Lists.newArrayList();
        List<String> nameUsed = new ArrayList<>();
        List<String> idUsed = new ArrayList<>();
        for(String s:openLists){
            String[] columns = s.split("\u0001");
            String nameMD5 = columns[3];
            String idNoMD5 = columns[5];
            String bankCardNo = columns[10];
            // columns[]

            if (NAME_HASH_MAP.containsKey(nameMD5)) {
                columns[3] = NAME_HASH_MAP.get(nameMD5);
                nameUsed.add(columns[3]);
            } else {
                String name =  NAME_HASH_MAP.values().stream().filter(s1 -> !nameUsed.contains(s1)).findFirst().get();
                columns[3] = name;
                nameUsed.add(columns[3]);
            }
            if (ID_HASH_MAP.containsKey(idNoMD5)) {
                columns[5] = ID_HASH_MAP.get(idNoMD5);
                idUsed.add(columns[5]);
            } else {
                String id =ID_HASH_MAP.values().stream().filter(s1 -> !nameUsed.contains(s1)).findFirst().get();
                columns[5] = id;
                idUsed.add(columns[5]);
            }
            cardNo++;
            columns[10] = String.valueOf(cardNo);
            washed.add(String.join("\u0001", columns));
        }
        FileUtil.writeLines(washed, dest + "xmxdzd_open_20220710.txt", StandardCharsets.UTF_8);
    }

    private static void washCreditFile(String xmzdFile, String x) {
        List<String> lists = FileUtil.readLines(xmzdFile, StandardCharsets.UTF_8);
        List<String> washed = Lists.newArrayList();
        for (String s : lists) {
            String[] columns = s.split("\u0001");
            String nameMD5 = columns[9];
            String idNoMD5 = columns[11];
            String mobileMD5 = columns[12];
            String signOfficeMd5 = columns[18];

            if (NAME_HASH_MAP.containsKey(nameMD5)) {
                columns[9] = NAME_HASH_MAP.get(nameMD5);
            } else {
                columns[9] = nameGen.generate();
                NAME_HASH_MAP.put(nameMD5, columns[9]);
            }

            if (ID_HASH_MAP.containsKey(idNoMD5)) {
                columns[11] = ID_HASH_MAP.get(idNoMD5);
            } else {
                columns[11] = idGen.generate();
                ID_HASH_MAP.put(idNoMD5, columns[11]);
            }

            if (MOBILE_HASH_MAP.containsKey(mobileMD5)) {
                columns[12] = MOBILE_HASH_MAP.get(mobileMD5);
            } else {
                columns[12] = mobileGen.generate();
                MOBILE_HASH_MAP.put(mobileMD5, columns[12]);
            }

            columns[18] = "成都市武侯区城南分局";

            washed.add(String.join("\u0001", columns));
        }
        FileUtil.writeLines(washed, dest + x, StandardCharsets.UTF_8);
    }
}
