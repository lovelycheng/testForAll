package com.example.tests.javafile;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author chengtong
 * @date 2021/10/27 10:20
 */


public class JavaFileProcessor {

    public static HashMap<ClassModel, List<MethodModel>> CLASS_MAP = new HashMap<>();

    public static void main(String[] args) {

        String basePath = new String("/Users/chengtong/demo");
        String module = basePath.split("/")[2];
        File base = new File(basePath);

        dig(base,module);

    }

    private static void dig(File base, String module) {
        if (base.isDirectory() && base.setReadable(true)) {
            File[] files = base.listFiles();
            if(files == null){
                return;
            }

            for (File file : files) {
                dig(file,module);
            }
        } else {
            ClassModel classModel = new ClassModel();
            classModel.setFile(base);
            classModel.setModule(module);
            String name = base.getName();

            if(isNotJavaFile(name)){
                return;
            }

            BufferedReader reader;
            try{
                reader = new BufferedReader(new FileReader(base));
                String firstLine = reader.readLine();
                if(StringUtils.isEmpty(firstLine)){
                    while(StringUtils.isEmpty(firstLine)){
                        firstLine = reader.readLine();
                    }

                    if(!firstLine.startsWith(JavaFileConstance.KEYWORD_PACKAGE)){
                        return;
                    }

                    String[] pk = firstLine.split(" ");
                    assert pk[0].equals(JavaFileConstance.KEYWORD_PACKAGE);
                    classModel.setPackagePath(pk[1]);



                    MethodFlag methodFlag = new MethodFlag();


                }


            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    private static boolean isNotJavaFile(String name) {
        return !name.endsWith(JavaFileConstance.KEYWORD_JAVA_SUFFIX);
    }

    private static class MethodFlag{

        private boolean flag;

        private String className;

        public boolean getFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

    }
}
