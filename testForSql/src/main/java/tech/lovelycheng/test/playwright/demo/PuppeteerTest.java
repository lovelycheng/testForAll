// package tech.lovelycheng.test.playwright.demo;
//
// import java.io.File;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.util.List;
// import java.util.Map;
//
// /**
//  * @author chengtong
//  * @date 2022/4/8 16:04
//  */
// public class PuppeteerTest {
//
//     static String sourcePath = "/Users/chengtong/mid-document/release_resources/release";
//     static String destPath = "";
//
//     public static void main(String[] args) {
//         // File file = new File(filePath);
//         // File dest = new File(fileDestPath);
//         if(dest.list().length > 0){
//             for (File file1:dest.listFiles()){
//                 file.delete();
//             }
//         }
//         File[] releaseVersions = file.listFiles();
//         for (File releaseVersion : releaseVersions) {
//             String version = releaseVersion.getName()
//                 .split("-")[0];
//             if (version.compareTo("3.0.5.1") > 0) {
//
//                 File increment = new File(
//                     releaseVersion.getAbsolutePath() + File.separator + "增量" + File.separator + "2.apollo配置说明");
//                 File[] modules = increment.listFiles();
//                 if (modules == null) {
//                     System.err.println("skip file:" + increment.getAbsolutePath());
//                     continue;
//                 }
//                 for (File module : modules) {
//                     if (module.isDirectory()) {
//                         continue;
//                     }
//                     if (!module.canRead()) {
//                         continue;
//                     }
//                     if (module.getName()
//                         .startsWith(".")) {
//                         continue;
//                     }
//                     System.err.println("read file " + module.getAbsolutePath());
//                     readFromFile(module, version);
//                 }
//             }
//         }
//
//         for (Map.Entry<String, List<ApolloKeyValueDto>> entry : hashMap.entrySet()) {
//             String fileName = entry.getKey();
//             List<ApolloKeyValueDto> valueDtos = entry.getValue();
//             if (valueDtos.isEmpty()) {
//                 continue;
//             }
//             File file1 = new File(fileDestPath + fileName + ".properties");
//
//             StringBuilder stringBuilder = new StringBuilder();
//             valueDtos.forEach((valueDto) -> stringBuilder.append(valueDto.toProperties()));
//             Files.write(stringBuilder.toString()
//                 .getBytes(StandardCharsets.UTF_8), file1);
//         }
//         System.err.println(JSON.toJSONString(hashMap));
//     }
// }
