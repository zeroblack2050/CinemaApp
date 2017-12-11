package com.cinemaapp.views.billboard;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cinemaapp.R;
import com.cinemaapp.helper.Constants;
import com.cinemaapp.helper.CustomSharedPreferences;
import com.cinemaapp.helper.Permissions;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

public class SessionWithTwitter extends BaseViews<SessionPresenter> implements ISessionWithTwitter {

    private TwitterLoginButton twitterLoginButton;
    private TwitterSession twitterSessionUser;

    private Button closeTwitterSession;
    private ImageView profilePhoto,imageGallery, imageCamera;
    private TextView nameUser, likes, followers, followings,sessionMessage;
    private Button backButtonTwitter;
    private BottomSheetDialog bottomSheetDialog;
    private String arrayFiles;
    private File photoFile;
    private CustomSharedPreferences sharedPreferences;

    private AlphaAnimation enterAnimation, exitAnimation;
    private Sequence sequence;
    private LinearLayout linearLayout;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.session_with_twitter);
        loadComponents();
        loginTwitter(this);
        showCustomDialog();
        sessionButtonActions();
        changeButtons();
        setDataFromSharedPreferences();
        initAnimationTour();
        runAnimationTour();

    }

    /*
    ** Init methods Tour Guide
    **/

    private void initAnimationTour() {
        enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);
        enterAnimation.setInterpolator(new BounceInterpolator());
        exitAnimation = new AlphaAnimation(0f, 1f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);
    }

    private void runAnimationTour() {

        ChainTourGuide tourGuideBillboard = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.session_with_twitter_photo))
                        .setDescription(getString(R.string.session_with_twitter_photo_description))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(profilePhoto);

        ChainTourGuide tourGuideMenu = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setTitle(getString(R.string.session_with_twitter_button))
                        .setDescription(getString(R.string.session_with_twitter_button_description))
                        .setGravity(Gravity.BOTTOM)

                )
                .playLater(linearLayout);

        sequence = new Sequence.SequenceBuilder()
                .add(tourGuideBillboard,tourGuideMenu)
                .setDefaultOverlay(new Overlay()
                        .setEnterAnimation(enterAnimation)
                        .setExitAnimation(exitAnimation)

                ).setDefaultPointer(null)
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();

        if (sharedPreferences.getTourGuide(Constants.SESSION_TOUR_GUIDE_KEY) != Constants.TOUR_GUIDE_MADE){
            ChainTourGuide.init(this).playInSequence(sequence);
            sharedPreferences.saveTourGuide(Constants.SESSION_TOUR_GUIDE_KEY,Constants.TOUR_GUIDE_MADE);
        }
        //sharedPreferences.deleteTourGuide(Constants.SESSION_TOUR_GUIDE_KEY);

    }


    //Start: Option Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_session_tour_guide){
            sharedPreferences.deleteTourGuide(Constants.SESSION_TOUR_GUIDE_KEY);
            runAnimationTour();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_session_twitter,menu);

        return super.onCreateOptionsMenu(menu);
    }
    //End: Option Menu




    /*
    ** End methods Tour Guide
    **/

    /*
    ** Init methods to capture or upload photos
    **/
    private void showCamera(){
        if(Permissions.isGrantedPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            showCameraIntent();
        }else {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            Permissions.verifyPermissions(this,permissions);
        }
    }
    private void showCameraIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(Intent.EXTRA_MIME_TYPES, true);
        photoFile = null;
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            try {
                photoFile = createImageFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (photoFile != null) {
            Uri photoUri = FileProvider.getUriForFile(this,"com.cinemaapp",photoFile);
            List<ResolveInfo> resolveInfoList = getPackageManager().
                    queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resolveInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                grantUriPermission(packageName, photoUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION |
                                Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            super.startActivityForResult(takePictureIntent,Constants.CAMERA_CAPTURE);
        }
    }
    @Nullable
    private File createImageFile() throws IOException {
        String imageFileName = Constants.PREFIX_FILE +
                new SimpleDateFormat(Constants.FORMAT_DATE_FILE).format(new Date());
        File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (storageDir != null && !storageDir.exists()) {
            boolean result = storageDir.mkdir();
            if (!result) {
                return null;
            }
        }
        return File.createTempFile(imageFileName, Constants.SUFFIX_FILE_IMAGE,storageDir);
    }
    private void showGallery(){
        if(Permissions.isGrantedPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            showGalleryIntent();
        }else {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE};
            Permissions.verifyPermissions(this,permissions);
        }
    }
    private void showGalleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if(Build.VERSION.SDK_INT < 19){
            startActivityForResult(intent, Constants.GALLERY_KITKAT);
        }else {
            String[] type = {"image/*"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, type);
            //intent.putExtra(Intent.EXTRA.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(intent,Constants.GALLERY);
        }
    }
    private void resultCameraCapture() {
        if (photoFile != null) {
            setArrayFilesName(photoFile.getPath(), Constants.FROM_CAMERA);
        }
    }
    @TargetApi(16)
    private void resultGalleryKitkatHigher(Intent data) {
        ClipData clipData = data.getClipData();

        if (clipData == null){
            resultGalleryKitkatLess(data.getData());
        }else{
            for (int i=0; i <clipData.getItemCount(); i++){
                grantUriPermission(getPackageName(), clipData.getItemAt(i).getUri(),Intent.FLAG_GRANT_READ_URI_PERMISSION);

                setArrayFilesName(clipData.getItemAt(i).getUri().toString(), Constants.FROM_GALLERY);
            }
        }


    }
    private void resultGalleryKitkatLess(Uri uri) {
        grantUriPermission(getPackageName(),uri,Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Log.e("resultGalleryKitka 283", ""+uri.getPath());
        setArrayFilesName(uri.toString(), Constants.FROM_GALLERY);
    }
    private void setArrayFilesName(String file, int from) {
        if (from == Constants.FROM_CAMERA){
            arrayFiles = Constants.SUFFIX_LOCAL_FILE+file;

        }
        if (from == Constants.FROM_GALLERY){
            arrayFiles = file;
        }
        Log.e("SetFileName 292", ""+file );
        sharedPreferences.saveTwitterData(Constants.IMAGE_KEY,arrayFiles);

        Picasso
                .with(this)
                .load(arrayFiles)
                .resize(140, 150)
                .centerCrop()
                .error(R.drawable.image_not_founded)
                .into(profilePhoto);
        bottomSheetDialog.dismiss();
    }

    /*
    ** End methods to capture or upload photos
    **/

    private void sessionButtonActions() {
        backButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        closeTwitterSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setDataComponents("file:","","","","");
                deleteMySharedPreferences(
                        Constants.IMAGE_KEY,
                        Constants.USER_KEY,
                        Constants.LIKES_KEY,
                        Constants.FOLLOWER_KEY,
                        Constants.FOLLOWING_KEY);

                closeTwitterSession.setVisibility(View.GONE);
                twitterLoginButton.setVisibility(View.VISIBLE);
                sharedPreferences.saveStateButtons(Constants.CHANGE_BUTTON,Constants.BUTTON_TWITTER_VISIBLE);
            }
        });
        profilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        imageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGallery();
            }
        });
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCamera();
            }
        });
    }
    private void showCustomDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_change_image,null);
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        imageGallery = view.findViewById(R.id.session_twitter_gallery);
        imageCamera = view.findViewById(R.id.session_twitter_camera);
    }
    private void loadComponents() {

        sharedPreferences = new CustomSharedPreferences(this);
        twitterLoginButton = findViewById(R.id.session_twitter_button_twitter);
        closeTwitterSession = findViewById(R.id.session_twitter_button_close);
        profilePhoto = findViewById(R.id.session_photo);
        nameUser = findViewById(R.id.session_name_user);
        likes = findViewById(R.id.session_likes);
        followers = findViewById(R.id.session_followers);
        followings = findViewById(R.id.session_following);
        sessionMessage = findViewById(R.id.session_message);
        backButtonTwitter = findViewById(R.id.session_close);
        linearLayout = findViewById(R.id.session_twitter_linear_buttons);
        toolbar = findViewById(R.id.session_toolbar_menu);
        toolbar.setTitle(Constants.EMPTY);
        setSupportActionBar(toolbar);
    }

    private void setDataComponents(
            String nUri,
            String nUser,
            String nLikes,
            String nFWes,
            String nFWing){
        Log.e("setDataComponents","  385   "+sharedPreferences.getTwitterData(Constants.IMAGE_KEY));

        Picasso
                .with(this)
                .load(nUri)
                .resize(140, 150)
                .centerCrop()
                .error(R.drawable.image_not_founded)
                .into(profilePhoto);
        nameUser.setText(nUser);
        likes.setText(nLikes);
        followers.setText(nFWes);
        followings.setText(nFWing);
        sessionMessage.setText("");
    }

    private void saveToSharedPreferences(String nUri, String nUser, String nLikes, String nFWes, String nFWing){
        sharedPreferences.saveTwitterData(Constants.IMAGE_KEY,nUri);
        sharedPreferences.saveTwitterData(Constants.USER_KEY,nUser);
        sharedPreferences.saveTwitterData(Constants.LIKES_KEY,nLikes);
        sharedPreferences.saveTwitterData(Constants.FOLLOWER_KEY,nFWes);
        sharedPreferences.saveTwitterData(Constants.FOLLOWING_KEY,nFWing);
    }
    private void deleteMySharedPreferences(String imageKey, String userKey, String likesKey, String followerKey, String followingKey) {
        sharedPreferences.deleteTwitterData(imageKey);
        sharedPreferences.deleteTwitterData(userKey);
        sharedPreferences.deleteTwitterData(likesKey);
        sharedPreferences.deleteTwitterData(followerKey);
        sharedPreferences.deleteTwitterData(followingKey);
    }

    private void setDataFromSharedPreferences(){
        if (
                sharedPreferences.getTwitterData(Constants.IMAGE_KEY) != null ||
                sharedPreferences.getTwitterData(Constants.USER_KEY) != null &&
                sharedPreferences.getTwitterData(Constants.LIKES_KEY) != null &&
                sharedPreferences.getTwitterData(Constants.FOLLOWER_KEY) != null &&
                sharedPreferences.getTwitterData(Constants.FOLLOWING_KEY) != null
                ){
            setDataComponents(
                    sharedPreferences.getTwitterData(Constants.IMAGE_KEY),
                    sharedPreferences.getTwitterData(Constants.USER_KEY),
                    sharedPreferences.getTwitterData(Constants.LIKES_KEY),
                    sharedPreferences.getTwitterData(Constants.FOLLOWER_KEY),
                    sharedPreferences.getTwitterData(Constants.FOLLOWING_KEY));
        }
    }
    public void changeButtons(){
        if (sharedPreferences.getStateButtons(Constants.CHANGE_BUTTON) == Constants.BUTTON_LOGOUT_VISIBLE){
            closeTwitterSession.setVisibility(View.VISIBLE);
            twitterLoginButton.setVisibility(View.GONE);
        }else if (sharedPreferences.getStateButtons(Constants.CHANGE_BUTTON) == Constants.BUTTON_TWITTER_VISIBLE){
            closeTwitterSession.setVisibility(View.GONE);
            twitterLoginButton.setVisibility(View.VISIBLE);
        }
    }
    //Init method Twitter:

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constants.GALLERY_KITKAT){
            resultGalleryKitkatLess(data.getData());
        }
        if(requestCode == Constants.GALLERY){
            resultGalleryKitkatHigher(data);
        }

        if(requestCode == Constants.CAMERA_CAPTURE){
            resultCameraCapture();
        }

        twitterLoginButton.onActivityResult(requestCode,resultCode,data);
    }

    private void loginTwitter(final Context context) {

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken twitterAuthToken = twitterSession.getAuthToken();

                twitterSessionUser = twitterSession;

                Call<User> userCall = TwitterCore
                        .getInstance()
                        .getApiClient(twitterSession)
                        .getAccountService()
                        .verifyCredentials(true,true,true);

                userCall.enqueue (new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        String description = result.data.description;

                        saveToSharedPreferences(
                                ""+result.data.profileImageUrlHttps,
                                ""+result.data.name+"\n"+result.data.screenName,
                                ""+result.data.favouritesCount,
                                ""+result.data.followersCount,
                                ""+result.data.friendsCount);

                        setDataComponents(
                                ""+result.data.profileImageUrlHttps,
                                ""+result.data.name+"\n"+result.data.screenName,
                                ""+result.data.favouritesCount,
                                ""+result.data.followersCount,
                                ""+result.data.friendsCount);

                        Log.e("ChangeButton","  516   ");
                        twitterLoginButton.setVisibility(View.GONE);
                        closeTwitterSession.setVisibility(View.VISIBLE);
                        sharedPreferences.saveStateButtons(Constants.CHANGE_BUTTON,Constants.BUTTON_LOGOUT_VISIBLE);


                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(SessionWithTwitter.this, ""+exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                if (twitterSessionUser != null){
                    twitterLoginButton.setVisibility(View.GONE);
                    closeTwitterSession.setVisibility(View.VISIBLE);
                }
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
