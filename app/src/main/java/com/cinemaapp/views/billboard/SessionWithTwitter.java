package com.cinemaapp.views.billboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cinemaapp.R;
import com.cinemaapp.presenters.SessionPresenter;
import com.cinemaapp.views.Bases.BaseViews;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;

public class SessionWithTwitter extends BaseViews<SessionPresenter> implements ISessionWithTwitter {

    private TwitterLoginButton twitterLoginButton;
    private ImageView profilePhoto;
    private TextView nameUser, likes, followers, followings,sessionMessage;
    private Button sessionClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.session_with_twitter);
        loadTwitterComponent();
        loginTwitter(this);
        sessionCloseButton();
    }

    private void sessionCloseButton() {
        sessionClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessionWithTwitter.this, BillboardList.class);
                startActivity(intent);
            }
        });
    }

    private void loadTwitterComponent() {
        twitterLoginButton = findViewById(R.id.session_twitter_button);
        profilePhoto = findViewById(R.id.session_photo);
        nameUser = findViewById(R.id.session_name_user);
        likes = findViewById(R.id.session_likes);
        followers = findViewById(R.id.session_followers);
        followings = findViewById(R.id.session_following);
        sessionMessage = findViewById(R.id.session_message);
        sessionClose = findViewById(R.id.session_close);

    }

    //Init method Twitter:

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode,resultCode,data);
    }

    private void loginTwitter(final Context context) {
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken twitterAuthToken = twitterSession.getAuthToken();
                Call<User> userCall = TwitterCore
                        .getInstance()
                        .getApiClient(twitterSession)
                        .getAccountService()
                        .verifyCredentials(true,true,true);


                userCall.enqueue (new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        String description = result.data.description;

                        Picasso
                                .with(context)
                                .load(result.data.profileImageUrlHttps)
                                .resize(140, 150)
                                .centerCrop()
                                .error(R.drawable.image_not_founded)
                                .into(profilePhoto);

                        nameUser.setText(result.data.name+"\n"+result.data.screenName);
                        int a = result.data.followersCount;
                        likes.setText(""+result.data.friendsCount);
                        followers.setText(""+result.data.followersCount);
                        followings.setText(""+result.data.friendsCount);

                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(SessionWithTwitter.this, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(SessionWithTwitter.this, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //End method Twitter.



    @Override
    public void showToast(int message) {

    }

    @Override
    public void showToast(String message) {

    }
}
