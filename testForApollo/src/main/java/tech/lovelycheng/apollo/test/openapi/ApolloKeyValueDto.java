package tech.lovelycheng.apollo.test.openapi;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/5/16 21:15
 */
@Data
public class ApolloKeyValueDto {

    /**
     * 注释
     */
    String comment;
    /**
     * 注释
     */
    String appId;
    /**
     * key
     */
    String key;
    String value;
    /**
     * 负责人
     */
    String operate;
    /**
     * 版本
     */
    String version;
    /**
     * 模块
     */
    String module;
    /**
     * 命名空间
     */
    String nameSpace;

    /**
     * 操作类型（新增、删除）
     */
    String type;

    public String toProperties(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("# ");
        stringBuilder.append(this.comment);
        stringBuilder.append(" ");
        stringBuilder.append("版本：");
        stringBuilder.append(version);
        stringBuilder.append(" ");
        stringBuilder.append("负责人：");
        stringBuilder.append(operate);
        stringBuilder.append(" ");
        stringBuilder.append("操作：");
        stringBuilder.append(type);
        stringBuilder.append("\n");

        stringBuilder.append(key);
        stringBuilder.append(" = ");
        stringBuilder.append(value);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
