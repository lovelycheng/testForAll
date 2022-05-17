package tech.lovelycheng.apollo.test.openapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenEnvClusterDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author chengtong
 * @date 2022/4/8 14:17
 */

public class OpenApiStart {

    String GetNameSpaces = " http://%portal_address/openapi/v1/apps/%appId/envclusters";

    static String HEADER = "| appid | namespace | 配置key | 配置value | 配置说明 | 操作 | 维护人 |\n"
        + "| ----- | --------- | ------- | --------- | -------- | ---- | ------ |\n";

    static String path
        = "/Users/chengtong/retail-mid/release_resources/snapshot/CONSUME-LOAN/3.0.5.X-SNAPSHOT/iteration1/02.apollo升级说明";

    public static void main(String[] args) throws IOException {
        String portalUrl = "http://10.168.4.21:6070"; // portal url

        String token = "b4e05b693a5bb197ed70ef273fa75af4c7e6eabb"; // 申请的token

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
            .withPortalUrl(portalUrl)
            .withToken(token)
            .build();
        String appid = "loan3.0-package-retail-product";
        List<OpenEnvClusterDTO> list = client.getEnvClusterInfo("loan3.0-package-retail-product");
        list.forEach(openEnvClusterDTO -> System.err.println(JSON.toJSONString(openEnvClusterDTO)));

        List<OpenNamespaceDTO> openNamespaceDTOS = client.getNamespaces(appid, "DEV", "loan-3052-tjbh");
        openNamespaceDTOS.forEach(openEnvClusterDTO -> System.err.println(JSON.toJSONString(openEnvClusterDTO)));
        List<SingleConfig> singleConfigs = new ArrayList<>();
        for (OpenNamespaceDTO openNamespaceDTO : openNamespaceDTOS) {
            List<OpenItemDTO> items = openNamespaceDTO.getItems();
            for (OpenItemDTO openItemDTO : items) {
                if (StringUtils.isEmpty(openItemDTO.getKey())) {
                    continue;
                }
                SingleConfig singleConfig = new SingleConfig();
                singleConfig.setAppId(appid);
                singleConfig.setCluster("loan3-0");
                singleConfig.setEnv("DEV");
                singleConfig.setKey(openItemDTO.getKey());
                singleConfig.setNamespace(openNamespaceDTO.getNamespaceName());
                singleConfig.setValue(openItemDTO.getValue());
                singleConfig.setRemark(openItemDTO.getComment());
                singleConfig.setMaintainer("chengt26050");
                singleConfigs.add(singleConfig);
            }
        }

        File file = new File(path + File.separatorChar + appid.substring("loan3.0-".length()) + ".md");
        file.delete();
        if (file.createNewFile()) {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(HEADER);
            for (SingleConfig singleConfig : singleConfigs) {
                fileWriter.write(singleConfig.toString());
            }
            fileWriter.flush();
            fileWriter.close();
        }

    }

}
