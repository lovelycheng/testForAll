package tech.lovelycheng.apollo.test.openapi;

import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenEnvClusterDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author chengtong
 * @date 2022/4/8 14:17
 */
@Slf4j
public class OpenApiStart {

    String GetNameSpaces = " http://%portal_address/openapi/v1/apps/%appId/envclusters";


    public static void main(String[] args) {
        String portalUrl = "http://10.168.4.21:6070"; // portal url

        String token = "b4e05b693a5bb197ed70ef273fa75af4c7e6eabb"; // 申请的token

        ApolloOpenApiClient client = ApolloOpenApiClient.newBuilder()
                .withPortalUrl(portalUrl)
                .withToken(token)
                .build();

        List<OpenEnvClusterDTO> list = client.getEnvClusterInfo("loan3.0-mmloan");
        list.forEach(System.err::println);

        log.info("环境:{},");


    }

}
