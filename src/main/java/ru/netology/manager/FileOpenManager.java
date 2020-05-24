package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

@AllArgsConstructor
@NoArgsConstructor
public class FileOpenManager {
    HashMap<String, String> maps = new HashMap<>();

    public HashMap<String, String> getMaps() {
        return maps;
    }

    public void registerApp(String file, String app) {
        maps.put(file, app);
    }

    public String getApp(String file) {
        return maps.get(file);
    }

    public void removeKey(String file) {
        maps.remove(file);
    }

    public List<String> getAllKeys() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> files = new HashSet<>();
        files.addAll(maps.keySet());
        ArrayList<String> listFiles = new ArrayList<>(files);
        listFiles.sort(byAlphabet);
        return listFiles;
    }

    public List<String> getAllValues() {
        Comparator byAlphabet = Comparator.naturalOrder();
        Set<String> files = new HashSet<>();
        files.addAll(maps.values());
        ArrayList<String> listFiles = new ArrayList<>(files);
        listFiles.sort(byAlphabet);
        return listFiles;
    }
}
