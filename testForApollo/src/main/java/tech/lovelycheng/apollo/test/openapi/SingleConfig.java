package tech.lovelycheng.apollo.test.openapi;

import lombok.Data;

/**
 * @author chengtong
 * @date 2022/5/7 14:06
 */
@Data
public class SingleConfig {

    private String appId;
    private String namespace;
    private String cluster;
    private String env;

    private String key;
    private String value;
    private String remark;
    private String maintainer;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    @Override
    public String toString() {
        return "| "+appId+" | "+namespace+" | "+key+" | "+value+" | "+remark+" | "+"新增"+" | "+maintainer+" |"+"\n";
    }
}
