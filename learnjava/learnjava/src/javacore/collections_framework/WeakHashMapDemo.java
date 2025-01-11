package javacore.collections_framework;

import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        WeakHashMapDemo obj = new WeakHashMapDemo();
        obj.demoWeakHashMap();
    }

    private void demoWeakHashMap() {

        /*
        Mainly used with caching.
        GC can clear the weak reference entry if the key is not in use.
         */

        WeakHashMap<String, Image> imageCache = new WeakHashMap<>();
        String key1 = new String("img1");
        String key2 = new String("img2");
        imageCache.put(key1, new Image("Image 1"));
        imageCache.put(key2, new Image("Image 2"));
        System.out.println(imageCache);
        key1 = null;
        key2 = null;

        System.gc();
        simulateApplicationRunning();

        System.out.println("Cache after running (some entries may be cleared): " + imageCache);

    }

    private void simulateApplicationRunning() {
        try {
            System.out.println("Simulating running application...");
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Image {
    private String name;

    public Image(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                '}';
    }
}
