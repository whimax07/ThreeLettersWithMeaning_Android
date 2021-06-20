package uk.co.whitehouse.max.threeletteracronymsv2.ui.main.list_acronyms;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import uk.co.whitehouse.max.threeletteracronymsv2.R;
import uk.co.whitehouse.max.threeletteracronymsv2.data.AcronymModel;

/**
 * A fragment representing a list of Items.
 */
public class DisplayAcronymFragment extends Fragment {

    private int mColumnCount = 1;
    private static final String ARG_COLUMN_COUNT = "column-count";

    private MyDisplayAcronymRecyclerViewAdapter recyclerViewAdapter;
    private final AcronymModel acronymModel = AcronymModel.getInstance();

    public static DisplayAcronymFragment newInstance() {
        return new DisplayAcronymFragment();
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DisplayAcronymFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerViewAdapter = new MyDisplayAcronymRecyclerViewAdapter();
            recyclerView.setAdapter(recyclerViewAdapter);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (acronymModel.isUpdated() && recyclerViewAdapter != null) {
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public MyDisplayAcronymRecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }
}