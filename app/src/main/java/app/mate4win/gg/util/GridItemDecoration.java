/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/


import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;
    private int headerNum;

    public GridItemDecoration(int spanCount, int spacing, boolean includeEdge, int headerNum) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
        this.headerNum = headerNum;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view) - headerNum; // item position

        if (position >= 0) {
            int column = position % spanCount; // item column

            if (!includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                //if(Config.getLayoutDirection() == LayoutDirection.LTR) {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
            /*}else{
                outRect.right = column * spacing / spanCount;
                outRect.left = spacing - (column + 1) * spacing / spanCount;
            }*/
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            outRect.bottom = 0;
        }
    }
}