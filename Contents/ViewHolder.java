package smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    FrameLayout root;

    public ViewHolder(FrameLayout itemView) {
        super(itemView);
        root = itemView;
    }

    public void setItem(View view) {
        root.addView(view);
    }

    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }

    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }
}
