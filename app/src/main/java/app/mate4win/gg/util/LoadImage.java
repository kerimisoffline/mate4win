/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

public class LoadImage {
    public LoadImage(){

    }

    public void load(Context context, String url, ImageView imageView, Boolean circle, int defaultImage){
        if(context == null || imageView == null)
            return;
        if(Config.isNotNull(url)){
            Log.d("imageuri", url);
            if(circle) {
                if(defaultImage > 0)
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultImage).transition(DrawableTransitionOptions.withCrossFade()).apply(RequestOptions.circleCropTransform()).into(imageView);
                else
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).into(imageView);
            }
            else {
                if(defaultImage > 0)
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).placeholder(defaultImage).into(imageView);
                else
                    Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
            }
        }
    }
}