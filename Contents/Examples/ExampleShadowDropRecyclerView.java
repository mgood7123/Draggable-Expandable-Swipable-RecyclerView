package smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents.Examples;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents.ExpandableView;
import smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents.RecyclerListAdapter;
import smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents.ShadowItemTouchHelper;
import smallville7123.example.taskbuilder.DraggableSwipableExpandableRecyclerView.Contents.SimpleShadowItemTouchHelperCallback;

import static android.R.drawable.ic_menu_delete;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ExampleShadowDropRecyclerView extends RecyclerView {

    static Drawable getDrawable(Context context, @DrawableRes int drawableID) {
        return context.getResources().getDrawable(drawableID, context.getTheme());
    }

    public static void onCreate(Activity activity) {
        ImageView trash = new ImageView(activity);
        trash.setImageDrawable(getDrawable(activity, ic_menu_delete));

        ExampleShadowDropRecyclerView shadowDropRecyclerView = new ExampleShadowDropRecyclerView(activity);

        LinearLayout layout = new LinearLayout(activity);
        layout.addView(
                shadowDropRecyclerView,
                new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f)
        );
        layout.addView(
                trash,
                new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1f) {
                    {
                        gravity = Gravity.RIGHT|Gravity.BOTTOM;
                    }
                }
        );
        activity.setContentView(layout);
    }

    RecyclerListAdapter adapter;
    public ExampleShadowDropRecyclerView(Context context) {
        this(context, null);
    }

    public ExampleShadowDropRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExampleShadowDropRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        adapter = new RecyclerListAdapter();
        setAdapter(adapter);
        setLayoutManager(new LinearLayoutManager(context));

        new ShadowItemTouchHelper(new SimpleShadowItemTouchHelperCallback(adapter)).attachToRecyclerView(this);

        // create 50 expandable items
        for (int i = 0; i < 50; i++) {
            addItem("Header " + i, "Content " + i);
        }
    }

    public void addItem(String headerName, String contentName) {
        // get context
        Context context = getContext();

        // create header
        TextView header = new TextView(context);
        header.setText(headerName);
        header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50f);

        // create context
        TextView content = new TextView(context);
        content.setText(contentName);
        content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f);

        // create toasts
        Toast headerToast = Toast.makeText(context, headerName + " clicked", Toast.LENGTH_SHORT);
        Toast contentToast = Toast.makeText(context, contentName + " clicked", Toast.LENGTH_SHORT);

        // create the expandable view and add it to the recycler view
        adapter.mItems.add(new ExpandableView(context) {
            {
                setHeader(header);
                setContent(content);
                setOnHeaderClicked(() -> {
                    contentToast.cancel();
                    headerToast.show();
                });
                setOnContentClicked(() -> {
                    headerToast.cancel();
                    contentToast.show();
                });
            }
        });
        adapter.notifyDataSetChanged();
    }
}
