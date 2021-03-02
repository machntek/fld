package com.machntek.fld.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.machntek.fld.annotation.FldElement;
import com.machntek.fld.dto.FldBody;
import com.machntek.fld.exception.FldParsingException;
import com.machntek.fld.exception.FldReflectionException;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FldParser {

    public static <T extends FldBody> T parseRequestBody(String data, Class<T> classOfT) throws FldParsingException{
        T instance = newInstance(classOfT);
        Field[] fileds = orderFldElementField(classOfT.getDeclaredFields());

        InputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        for(Field field: fileds) {
            field.setAccessible(true);
            int size = field.getAnnotation(FldElement.class).size();
            Class type = field.getType();

            try {
                String cuttedString = getDataFromFld(inputStream, size);
                if(type.equals(String.class)) {
                    field.set(instance, cuttedString);
                } else if(type.equals(int.class) || type.equals(Integer.class)) {
                    int numeric = Integer.parseInt(cuttedString);
                    field.set(instance, numeric);
                }
            } catch (IOException | RuntimeException | IllegalAccessException e) {
                throw new FldParsingException(e);
            }

        }
        return instance;
    }

    public static void parseHeader(InputStream input) {

    }

    public static void parseResponseBody() {}

    public static void parseResponseHeader() {}

    private static String getDataFromFld(InputStream inputStream, int size) throws IOException {
        int sizeOfInputStream = inputStream.available();
        if(sizeOfInputStream < size || sizeOfInputStream == 0) {
            throw new IOException();
        }
        return new String(inputStream.readNBytes(size), StandardCharsets.UTF_8);
    }

    private static Field[] orderFldElementField(Field[] fields) {
        Stream<Field> fieldStream = Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(FldElement.class))
                .sorted(Comparator.comparing(field -> field.getAnnotation(FldElement.class).order()));
        return fieldStream.toArray(Field[]::new);
    }

    private static <T extends FldBody> T newInstance(Class<T> classOfT) throws FldReflectionException{
        T instance = null;
        try {
            Constructor<T> constructor = classOfT.getConstructor();
            instance = constructor.newInstance();
        } catch (Exception e) {
            throw new FldReflectionException(e);
        }
        return instance;
    }

    // Todo
    // 1. padZero 2. padSpace 3. getValue
}
