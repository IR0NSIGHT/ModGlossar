import glossar.GlossarCategory;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * STARMADE MOD
 * CREATOR: Max1M
 * DATE: 11.12.2021
 * TIME: 19:04
 */
public class test {
    static ArrayList<GlossarCategory> categories = new ArrayList<>();
    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            char c = (char)(r.nextInt(26) + 'a');
            categories.add(new GlossarCategory(c+"-"));
        }
        categories.add(new GlossarCategory("WarpSpace"));


        Collections.sort(categories, new Comparator<GlossarCategory>() {
            @Override
            public int compare(GlossarCategory o1, GlossarCategory o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        for (GlossarCategory cat: categories
             ) {
            System.out.println(cat.getName());
        }
    }
}
