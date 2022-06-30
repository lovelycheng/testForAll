package tech.lovelycheng.demo.test.fileimport.easyimport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.geometry.spherical.oned.Arc;
import org.reflections.Reflections;

import cn.hutool.core.util.StrUtil;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.Archived;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.Column;
import tech.lovelycheng.demo.test.fileimport.easyimport.fo.Table;

/**
 * @author chengtong
 * @date 2022/6/28 15:04
 */
public class ImportConfiguration {

    private final HashMap<String, Class<? extends Archived>> archivedHashMap = new HashMap<>();
    private final Map<String, Map<String, Method>> classToSetMethod = new HashMap<>();
    private final Map<String, Map<String, Method>> classToGetMethod = new HashMap<>();

    public ImportConfiguration() throws NoSuchFieldException, NoSuchMethodException {
        Reflections reflections = new Reflections("tech.lovelycheng.demo.test.fileimport.easyimport");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Table.class);
        if (annotated == null || annotated.isEmpty()) {
            return;
        }
        for (Class<?> c : annotated) {
            Table annotation = c.getAnnotation(Table.class);
            if (annotation.value() != null ) {

                archivedHashMap.put(annotation.value(), (Class<? extends Archived>) c);
            }

            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(Column.class) != null) {
                    Method setMethod = Arrays.stream(c.getDeclaredMethods())
                        .filter(mt -> {

                            try {
                                return mt.getName()
                                    .equals("set" + StrUtil.upperFirst(c.getDeclaredField(field.getName())
                                        .getName()));
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                            return false;
                        })
                        .findFirst()
                        .orElse(null);
                    Method getMethod = Arrays.stream(c.getDeclaredMethods())
                        .filter(mt -> {

                            try {
                                return mt.getName()
                                    .equals("get" + StrUtil.upperFirst(c.getDeclaredField(field.getName())
                                        .getName()));
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                            return false;
                        })
                        .findFirst()
                        .orElse(null);

                    Column column = field.getAnnotation(Column.class);

                    classToSetMethod.computeIfAbsent(annotation.value(), k -> new HashMap<>());
                    classToSetMethod.get(annotation.value())
                        .put(column.name(), setMethod);

                    classToGetMethod.computeIfAbsent(annotation.value(), k -> new HashMap<>());
                    classToGetMethod.get(annotation.value())
                        .put(column.name(), getMethod);
                }
            }
        }

    }

    public Class<? extends Archived> getClassByFileName(String name) {
        return archivedHashMap.get(name);
    }

    public Map<String, Method> getClassSetMethodsByFileName(String name) {
        return classToSetMethod.get(name);
    }
    public Map<String, Method> getClassGetMethodsByFileName(String name) {
        return classToGetMethod.get(name);
    }

}
