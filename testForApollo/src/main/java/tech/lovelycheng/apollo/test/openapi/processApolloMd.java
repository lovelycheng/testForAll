package tech.lovelycheng.apollo.test.openapi;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * @author chengtong
 * @date 2022/5/16 20:24
 * 处理apollo md文件
 */
public class processApolloMd {

    /**
     * | appid| namespace   | 配置key | 配置value | 配置说明 | 操作 | 维护人 |
     */
    static Integer APPID_IDX = 1;
    static Integer NS_IDX = APPID_IDX + 1;
    static Integer KEY_IDX = NS_IDX + 1;
    static Integer VALUE_IDX = KEY_IDX + 1;
    static Integer COMMENT_IDX = VALUE_IDX + 1;
    static Integer TYPE_IDX = COMMENT_IDX + 1;
    static Integer OPERATE_IDX = TYPE_IDX + 1;

    final static String ADD_APPLICATION_KEY = "_application_add";
    final static String ADD_NS000_KEY = "_application_add";
    final static String ADD_CREP_KEY = "_CREP_SYSTEM_V3_add";

    final static String DEL_APPLICATION_KEY = "_application_del";
    final static String DEL_NS000_KEY = "_application_del";
    final static String DEL_CREP_KEY = "_CREP_SYSTEM_V3_del";

    static String filePath = "/Users/chengtong/mid-document/release_resources/release";
    static String fileDestPath = "/Users/chengtong/demo/testForApollo/src/main/resources/properties/";

    static HashMap<String, List<ApolloKeyValueDto>> hashMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        File file = new File(filePath);
        File dest = new File(fileDestPath);
        if(dest.list().length > 0){
            for (File file1:dest.listFiles()){
                file.delete();
            }
        }
        File[] releaseVersions = file.listFiles();
        for (File releaseVersion : releaseVersions) {
            String version = releaseVersion.getName()
                .split("-")[0];
            if (version.compareTo("3.0.5.1") > 0) {

                File increment = new File(
                    releaseVersion.getAbsolutePath() + File.separator + "增量" + File.separator + "2.apollo配置说明");
                File[] modules = increment.listFiles();
                if (modules == null) {
                    System.err.println("skip file:" + increment.getAbsolutePath());
                    continue;
                }
                for (File module : modules) {
                    if (module.isDirectory()) {
                        continue;
                    }
                    if (!module.canRead()) {
                        continue;
                    }
                    if (module.getName()
                        .startsWith(".")) {
                        continue;
                    }
                    System.err.println("read file " + module.getAbsolutePath());
                    readFromFile(module, version);
                }
            }
        }

        for (Map.Entry<String, List<ApolloKeyValueDto>> entry : hashMap.entrySet()) {
            String fileName = entry.getKey();
            List<ApolloKeyValueDto> valueDtos = entry.getValue();
            if (valueDtos.isEmpty()) {
                continue;
            }
            File file1 = new File(fileDestPath + fileName + ".properties");

            StringBuilder stringBuilder = new StringBuilder();
            valueDtos.forEach((valueDto) -> stringBuilder.append(valueDto.toProperties()));
            Files.write(stringBuilder.toString()
                .getBytes(StandardCharsets.UTF_8), file1);
        }
        System.err.println(JSON.toJSONString(hashMap));
    }

    static void readFromFile(File file, String version) throws Exception {
        String name = file.getName().split("\\.")[0];
        List<ApolloKeyValueDto> listApplicationAdd = Lists.newArrayList();
        List<ApolloKeyValueDto> listZZZAdd = Lists.newArrayList();
        List<ApolloKeyValueDto> listCrepAdd = Lists.newArrayList();
        List<ApolloKeyValueDto> listApplicationDel = Lists.newArrayList();
        List<ApolloKeyValueDto> listZZZDel = Lists.newArrayList();
        List<ApolloKeyValueDto> listCrepDel = Lists.newArrayList();

        List<String> lines = Files.readLines(file, StandardCharsets.UTF_8);
        for (String line : lines) {
            if (line.contains("----") || line.contains("appid") || line.contains("配置key")) {
                continue;
            }
            String[] elements = line.split("\\|");
            String type = elements[TYPE_IDX].trim();
            ApolloKeyValueDto apolloKeyValueDto = new ApolloKeyValueDto();
            apolloKeyValueDto.setKey(elements[KEY_IDX].trim());
            apolloKeyValueDto.setValue(elements[VALUE_IDX].trim());
            apolloKeyValueDto.setNameSpace(elements[NS_IDX].trim());
            apolloKeyValueDto.setOperate(elements[OPERATE_IDX].trim());
            apolloKeyValueDto.setComment(elements[COMMENT_IDX].trim());
            apolloKeyValueDto.setAppId(elements[APPID_IDX].trim());
            apolloKeyValueDto.setType(type);
            apolloKeyValueDto.setModule(name);
            apolloKeyValueDto.setVersion(version);

            String nameSpace = apolloKeyValueDto.getNameSpace()
                .trim();
            if (type.equals("ADD") || type.equals("新增")) {
                fillLists(nameSpace, apolloKeyValueDto, listApplicationAdd, listZZZAdd, listCrepAdd);
            } else {
                fillLists(nameSpace, apolloKeyValueDto, listApplicationDel, listZZZDel, listCrepDel);
            }
        }

        fillHashMap(name, ADD_APPLICATION_KEY, listApplicationAdd);
        fillHashMap(name, ADD_CREP_KEY, listCrepAdd);
        fillHashMap(name, ADD_NS000_KEY, listZZZAdd);

        fillHashMap(name, DEL_APPLICATION_KEY, listApplicationDel);
        fillHashMap(name, DEL_CREP_KEY, listCrepDel);
        fillHashMap(name, DEL_NS000_KEY, listZZZDel);

    }

    private static void fillLists(String nameSpace, ApolloKeyValueDto apolloKeyValueDto,
        List<ApolloKeyValueDto> listApplication, List<ApolloKeyValueDto> listZZZ, List<ApolloKeyValueDto> listCrep)
    throws Exception {
        if (nameSpace.equals("application")) {
            listApplication.add(apolloKeyValueDto);
        } else if (nameSpace.equals("000")) {
            listZZZ.add(apolloKeyValueDto);
        } else if (nameSpace.toLowerCase()
            .contains("crep")) {
            listCrep.add(apolloKeyValueDto);
        } else {
            System.err.println("无法解析的nameSpace：" + com.alibaba.fastjson2.JSON.toJSONString(apolloKeyValueDto));
            throw new Exception("无法解析的nameSpace");
        }
    }

    private static void fillHashMap(String name, String addApplicationKey, List<ApolloKeyValueDto> listApplicationAdd) {
        hashMap.compute(name + addApplicationKey, (s, apolloKeyValueDtos) -> {
            if (apolloKeyValueDtos == null) {
                apolloKeyValueDtos = listApplicationAdd;
            } else {
                apolloKeyValueDtos.addAll(listApplicationAdd);
            }
            return apolloKeyValueDtos;
        });
    }

}
