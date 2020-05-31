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
    private FileOpenManager manager = new FileOpenManager();

    private String html = "html";
    private String safari = "Safari";
    private String xml = "xml";
    private String google = "Google";
    private String img = "img";
    private String viewer = "Viewer";
    private String dox = "dox";
    private String keyInCaps = "HTML";

    @Nested
    public class EmptyManager {

        @Test
        void shouldReturnNullIfNoApp() {
            String expected = null;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToRemove() {
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(html);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetEmptyIfNoKeys() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetEmptyIfNoValues() {
            List<String> expected = new ArrayList<>();
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItemManager {

        @Test
        void shouldReturnApp() {
            manager.registerApp(html, safari);
            String expected = safari;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveKey() {
            manager.registerApp(html, safari);
            Map<String, String> expected = new HashMap<>();
            manager.removeKey(html);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetOneKey() {
            manager.registerApp(html, safari);
            List<String> expected = new ArrayList<>(List.of(html));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetOneValue() {
            manager.registerApp(html, safari);
            List<String> expected = new ArrayList<>(List.of(safari));
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItemsManager {

        @BeforeEach
        void setup() {
            manager = new FileOpenManager();
            manager.registerApp(html, safari);
            manager.registerApp(xml, google);
            manager.registerApp(img, viewer);
        }

        @Test
        void shouldAddAll() {
            HashMap<String, String> expected = new HashMap<>();
            expected.put(html, safari);
            expected.put(xml, google);
            expected.put(img, viewer);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnOneApp() {
            String expected = safari;
            String actual = manager.getApp(html);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnNullIfInvalidKey() {
            String expected = null;
            String actual = manager.getApp(dox);
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnNullIfKeyInCaps() {
            String expected = null;
            String actual = manager.getApp(keyInCaps);
            assertEquals(expected, actual);
        }

        @Test
        void shouldRemoveKey() {
            manager.removeKey(xml);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, safari);
            expected.put(img, viewer);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemoveKeyIfCaps() {
            manager.removeKey(keyInCaps);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, safari);
            expected.put(xml, google);
            expected.put(img, viewer);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldNotRemoveKeyIfInvalid() {
            manager.removeKey(dox);
            Map<String, String> expected = new HashMap<>();
            expected.put(html, safari);
            expected.put(xml, google);
            expected.put(img, viewer);
            HashMap<String, String> actual = manager.getMaps();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetAllKeysSorted() {
            manager.registerApp(html, safari);
            manager.registerApp(xml, google);
            manager.registerApp(img, viewer);
            List<String> expected = new ArrayList<>(List.of(html, img, xml));
            List<String> actual = manager.getAllKeys();
            assertEquals(expected, actual);
        }

        @Test
        void shouldGetAllValuesSorted() {
            manager.registerApp(html, safari);
            manager.registerApp(xml, google);
            manager.registerApp(img, viewer);
            List<String> expected = new ArrayList<>(List.of(google, safari, viewer));
            List<String> actual = manager.getAllValues();
            assertEquals(expected, actual);
        }
    }
}