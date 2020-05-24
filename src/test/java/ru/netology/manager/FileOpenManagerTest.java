package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileOpenManagerTest {
    FileOpenManager manager = new FileOpenManager();

    @Nested
    public class EmptyManager{

        @Test
        void shouldReturnNullIfNoApp(){
            String expected = null;
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToRemove(){
            Map<String, String> expected = new HashMap<>();
            manager.removeKey("html");
            HashMap<String,String> actual = manager.getMaps();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetEmptyIfNoKeys(){
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of());
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetEmptyIfNoValues(){
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of());
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }

    @Nested
    public class SingleItemManager{

        @Test
        void shouldReturnApp(){
            manager.registerApp("html","Safari");
            String expected = "Safari";
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldRemoveKey(){
            manager.registerApp("html","Safari");
            Map<String, String> expected = new HashMap<>();
            manager.removeKey("html");
            HashMap<String,String> actual = manager.getMaps();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetOneKey(){
            manager.registerApp("html","Safari");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("html"));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetOneValue(){
            manager.registerApp("html","Safari");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("Safari"));
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }

    @Nested
    public class MultipleItemsManager{

        @BeforeEach
        void setup() {
            manager = new FileOpenManager();
            manager.registerApp("html","Safari");
            manager.registerApp("xml","Google");
            manager.registerApp("img","Viewer");
        }

        @Test
        void shouldAddAll(){
            HashMap<String, String> expected = new HashMap<>();
            expected.put("html","Safari");
            expected.put("xml","Google");
            expected.put("img","Viewer");
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnOneApp(){
            String expected = "Safari";
            String actual = manager.getApp("html");
            assertEquals(expected,actual);
        }

        @Test
        void shouldReturnNullIfInvalidKey(){
            String expected = null;
            String actual = manager.getApp("dox");
            assertEquals(expected,actual);
        }

        @Test
        void shouldRemoveKey(){
            manager.removeKey("xml");
            Map<String, String> expected = new HashMap<>();
            expected.put("html","Safari");
            expected.put("img","Viewer");
            HashMap<String,String> actual = manager.getMaps();
            assertEquals(expected,actual);
        }

        @Test
        void shouldNotRemoveKeyIfInvalid(){
            manager.removeKey("dox");
            Map<String, String> expected = new HashMap<>();
            expected.put("html","Safari");
            expected.put("xml","Google");
            expected.put("img","Viewer");
            HashMap<String,String> actual = manager.getMaps();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetAllKeysSorted(){
            manager.registerApp("html","Safari");
            manager.registerApp("xml","Google");
            manager.registerApp("img","Viewer");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("html","img","xml"));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected,actual);
        }

        @Test
        void shouldGetAllValuesSorted(){
            manager.registerApp("html","Safari");
            manager.registerApp("xml","Google");
            manager.registerApp("img","Viewer");
            List<String> expected = new ArrayList<>();
            expected.addAll(List.of("Google","Safari","Viewer"));
            List<String> actual = manager.getAllValues();
            assertEquals(expected,actual);
        }
    }
}