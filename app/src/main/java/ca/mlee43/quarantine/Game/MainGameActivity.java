package ca.mlee43.quarantine.Game;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import ca.mlee43.quarantine.R;

public class MainGameActivity extends Activity implements WelcomeFragment.WelcomeFragmentListener {

    public static final String TAG_MAIN_GAME = "MainGameActivity";

    /**
     * Callback method for when the application is started. An instance of the
     * WelcomeFragment is created and attached to the activity.
     *
     * @param savedInstanceState A bundle containing information to recreate the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initiate activity state and set activity view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        // Create and attach welcome fragment
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_maingame, welcomeFragment,
                WelcomeFragment.TAG_WELCOME_FRAGMENT);
        fragmentTransaction.commit();
    }

    @Override
    public void onEnterLobby() {
        // TODO: 16-04-28 Switch to the lobby screen 
    }

    @Override
    public void onCreateLobby(String name) {
        // TODO: 16-04-28 Start server 
    }

    @Override
    public void onJoin(String ip, String name) {
        // TODO: 16-04-28 Establish connection with existing game
    }
}
