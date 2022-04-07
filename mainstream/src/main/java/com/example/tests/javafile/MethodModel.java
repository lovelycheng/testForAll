package com.example.tests.javafile;

import java.util.List;

/**
 * @author chengtong
 * @date 2021/10/27 10:22
 */

public class MethodModel {

    String name;

    String visit;

    String returnType;

    String staticType;

    String[] parameterTypes;

    String[] parameterNames;

    List<String> body;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getStaticType() {
        return staticType;
    }

    public void setStaticType(String staticType) {
        this.staticType = staticType;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String[] getParameterNames() {
        return parameterNames;
    }

    public void setParameterNames(String[] parameterNames) {
        this.parameterNames = parameterNames;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
