package com.example.tests.javafile;

import java.io.File;
import java.util.Objects;

/**
 * @author chengtong
 * @date 2021/10/27 10:32
 */
public class ClassModel {

    /**
     * 类名
     */
    private String className;

    /**
     * 包名
     */
    private String packagePath;

    /**
     * 模块
     */
    private String module;

    /**
     * java文件
     */
    private File file;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassModel)) return false;
        ClassModel that = (ClassModel) o;
        return getClassName().equals(that.getClassName()) && getPackagePath().equals(that.getPackagePath()) && getModule().equals(that.getModule()) && getFile().equals(that.getFile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClassName(), getPackagePath(), getModule(), getFile());
    }





}
