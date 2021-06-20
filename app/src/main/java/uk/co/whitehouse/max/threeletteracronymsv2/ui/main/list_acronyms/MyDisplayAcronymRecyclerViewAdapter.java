package uk.co.whitehouse.max.threeletteracronymsv2.ui.main.list_acronyms;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import uk.co.whitehouse.max.threeletteracronymsv2.data.Acronym;
import uk.co.whitehouse.max.threeletteracronymsv2.data.AcronymList;
import uk.co.whitehouse.max.threeletteracronymsv2.data.AcronymModel;
import uk.co.whitehouse.max.threeletteracronymsv2.databinding.FragmentItemBinding;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Acronym}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyDisplayAcronymRecyclerViewAdapter extends RecyclerView.Adapter<MyDisplayAcronymRecyclerViewAdapter.ViewHolder> {

    private final AcronymList mValues = AcronymModel.getInstance().getAcronymsList();

    public MyDisplayAcronymRecyclerViewAdapter() {
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(Integer.toString(position + 1));
        holder.mContentView.setText(mValues.get(position).getAcronym());
    }

    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Acronym mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}