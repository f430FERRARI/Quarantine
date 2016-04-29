package ca.mlee43.quarantine.Game;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.mlee43.quarantine.R;

public class LobbyFragment extends Fragment {

    public static final String TAG_LOBBY_FRAGMENT = "LobbyFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lobby, container, false);
    }

}
