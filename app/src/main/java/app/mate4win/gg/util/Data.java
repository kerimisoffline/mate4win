/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

import android.content.Context;

import java.util.List;

import app.mate4win.gg.model.Cache;
import app.mate4win.gg.model.Category;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.model.MainCategory;
import app.mate4win.gg.model.Member;
import app.mate4win.gg.model.Post;

public class Data {
    public static List<Category> categories;
    public static List<Groups> groups;
    public static List<Post> posts;
    public static List<MainCategory> mainCategories;
    private static Cache cache;
    public static Member member;
    public static Category selectedCategory;
    public static MainCategory selectedPlatform;
    public static Groups selectedGroups;

    public static Cache getCache(Context context){
        if(cache == null)
            cache = new Cache(context);

        return cache;
    }

}
