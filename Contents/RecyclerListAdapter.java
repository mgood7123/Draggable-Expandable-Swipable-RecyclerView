package smallville7123.DraggableSwipableExpandableRecyclerView.Contents;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class RecyclerListAdapter extends RecyclerView.Adapter<ViewHolder> {

    public final ArrayList<View> mItems = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FrameLayout fl = new FrameLayout(parent.getContext());
        fl.setLayoutParams(new FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        return new ViewHolder(fl);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setItem(mItems.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        holder.root.removeAllViews();
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
