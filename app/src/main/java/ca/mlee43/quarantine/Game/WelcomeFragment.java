package ca.mlee43.quarantine.Game;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.mlee43.quarantine.R;

public class WelcomeFragment extends Fragment implements Button.OnClickListener {

    public static final String TAG_WELCOME_FRAGMENT = "WelcomeFragment";

    private WelcomeFragmentListener mListener;

    Button playNowButton;

    public interface WelcomeFragmentListener {
        void onCreateLobby(String name);

        void onJoin(String ip, String name);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mListener = (WelcomeFragmentListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        playNowButton = (Button) view.findViewById(R.id.button_play_now);
        playNowButton.setOnClickListener(this);

        return view;
    }

    /**
     * Callback that is called when the play now button is pressed. It initiates the first dialog
     * asking for the players name and calls another dialog.
     *
     * @param v The view reference for the button that was pressed.
     */
    @Override
    public void onClick(View v) {
        // Build the dialog when play now is pressed
        final EditText nameBox = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(nameBox);
        builder.setMessage(R.string.name_prompt);
        builder.setTitle(R.string.but_first);
        builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String name = nameBox.getText().toString();

                // Check if entry was blank
                if (name != null && !name.isEmpty()) {
                    requestAction(name);
                } else {
                    Toast.makeText(getActivity(), "You must enter a name!", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).show();
    }

    /**
     * This method creates a dialog asking the player if they want to create or join a game. It calls
     * methods in MainGameActivity to create or join a game. A successful lobby creation calls a method
     * to create the LobbyFragment.
     *
     * @param name Name of the player as entered in the preceeding dialog.
     */
    private void requestAction(final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.let_us_begin);
        builder.setMessage(R.string.join_or_create);
        builder.setPositiveButton(R.string.create_game, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onCreateLobby(name);
            }
        })
                .setNegativeButton(R.string.join, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        requestIP(name);
                    }
                }).show();
    }

    /**
     * This method creates a dialog requesting the IP address of the game the player wants to join.
     * It calls a method in the MainGameActivity to join a game at the specified IP address. A
     * successful join calls a method to create the LobbyFragment.
     *
     * @param name Name of the player as entered in the preceeding dialog.
     */
    private void requestIP(final String name) {
        final EditText ipBox = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(ipBox);
        builder.setTitle(R.string.ip_address);
        builder.setMessage(R.string.ip_to_join);
        builder.setPositiveButton(R.string.join, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String ip = ipBox.getText().toString();
                if (ip != null && !ip.isEmpty()) {
                    mListener.onJoin(ip, name);
                } else {
                    Toast.makeText(getActivity(), "You must enter an IP Address!", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                }).show();
    }
}
