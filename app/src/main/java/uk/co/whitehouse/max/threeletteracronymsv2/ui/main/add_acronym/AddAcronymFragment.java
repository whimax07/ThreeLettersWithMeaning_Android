package uk.co.whitehouse.max.threeletteracronymsv2.ui.main.add_acronym;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

import uk.co.whitehouse.max.threeletteracronymsv2.R;
import uk.co.whitehouse.max.threeletteracronymsv2.data.Acronym;
import uk.co.whitehouse.max.threeletteracronymsv2.data.AcronymModel;
import uk.co.whitehouse.max.threeletteracronymsv2.data.E_AcronymAdded;
import uk.co.whitehouse.max.threeletteracronymsv2.data.SaveAcronyms;

public class AddAcronymFragment extends Fragment {

    private final AcronymModel acronymModel = AcronymModel.getInstance();
    private EditText newAcronymEditText;
    private TextView addedStateLabel;

    public static AddAcronymFragment newInstance() {
        return new AddAcronymFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_add_acronym, container, false);
        inflate.findViewById(R.id.addButton).setOnClickListener(this::addAcronym);
        newAcronymEditText = inflate.findViewById(R.id.newAcronymEditText);
        addedStateLabel = inflate.findViewById(R.id.addedState);

        TextView acronymCount = Objects.requireNonNull(inflate.findViewById(R.id.acronymCountText));
        acronymCount.setText(String.valueOf(acronymModel.getAcronymsList().size()));

        return inflate;
    }

    public void addAcronym(View view) {
        E_AcronymAdded added;
        String text = newAcronymEditText.getText().toString();
        if (text.length() != 3) {
            addedStateLabel.setText(R.string.pleaseEnterALetterAcronym);
        }
        Acronym candidate = new Acronym(text);
        if (newAcronym(candidate)) {
            candidate.setDateAdded(Calendar.getInstance().getTime());
            acronymModel.getAcronymsList().add(candidate);
            acronymModel.getAcronymsList().sort(Acronym::compareTo);
            added = E_AcronymAdded.ADDED;
        } else {
            added = E_AcronymAdded.NOT_ADDED;
        }
        updateGUI(added, candidate);
        if (added == E_AcronymAdded.ADDED) {
            SaveAcronyms.save(Objects.requireNonNull(getContext()), acronymModel.getAcronymsList());
            acronymModel.setUpdated(true);
        }
    }

    private boolean newAcronym(Acronym candidate) {
        if (acronymModel.getAcronymsList().size() == 0) {
            return true;
        }
        for (Acronym acronym : acronymModel.getAcronymsList()) {
            int dif = acronym.compareTo(candidate);
            if (dif == 0) {
                return false;
            } else if (dif < 0) {
                return true;
            }
        }
        return true;
    }

    private void updateGUI(E_AcronymAdded state, Acronym acronym) {
        if (state == E_AcronymAdded.ADDED) {
            addedStateLabel.setText(getString(R.string.added, acronym.getAcronym()));
        } else if (state == E_AcronymAdded.NOT_ADDED) {
            addedStateLabel.setText(getString(R.string.duplicate, acronym.getAcronym()));
        }
        TextView acronymCount = Objects.requireNonNull(getView()).findViewById(R.id.acronymCountText);
        acronymCount.setText(String.valueOf(acronymModel.getAcronymsList().size()));
        newAcronymEditText.setText("");
    }
}