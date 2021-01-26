package smallville7123.DraggableSwipableExpandableRecyclerView.Contents;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ViewHolder extends RecyclerView.ViewHolder {

    FrameLayout root;

    public ViewHolder(FrameLayout itemView) {
        super(itemView);
        root = itemView;
    }

    public void setItem(View view) {
        if(view.getParent() != null) ((ViewGroup)view.getParent()).removeView(view);
        root.addView(view, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
