package lk.sliit.eleven.bakeryorder.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Predicate;

public class FileStorageUtil<T> {

    private final String filePath;
    private final Class<T> clazz;
    private final Gson gson;

    public FileStorageUtil(String filePath, Class<T> clazz) {
        this.filePath = filePath;
        this.clazz = clazz;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(java.time.LocalDateTime.class, new LocalDateTimeAdapter())
                .setPrettyPrinting()
                .create();
    }

    private List<T> readFromFile() {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(reader, listType);
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeToFile(List<T> list) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CRUD

    public List<T> getAll() {
        return readFromFile();
    }

    public void add(T item) {
        List<T> list = readFromFile();
        list.add(item);
        writeToFile(list);
    }

    public void update(Predicate<T> matchFn, T newItem) {
        List<T> list = readFromFile();
        for (int i = 0; i < list.size(); i++) {
            if (matchFn.test(list.get(i))) {
                list.set(i, newItem);
                break;
            }
        }
        writeToFile(list);
    }

    public void delete(Predicate<T> matchFn) {
        List<T> list = readFromFile();
        list.removeIf(matchFn);
        writeToFile(list);
    }
}