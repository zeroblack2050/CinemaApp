package com.cinemaapp.helper;

/**
 * Created by leidyzulu on 16/09/17.
 */

public class Constants {

    public static final String URL_XML_MOVIE_SERVICES = "http://trailers.apple.com/trailers/home/xml";
    public static final String URL_JSON_CINEMAS_SERVICES = "http://shoppingproducts.herokuapp.com";


    public final static int TIME_OUT = 6;
    public static final String REQUEST_TIMEOUT_ERROR_MESSAGE = "La solicitud está tardando demasiado. Por favor intÃ©ntalo nuevamente.";
    public static final int DEFAULT_ERROR_CODE = 0;
    public static final String DEFAULT_ERROR = "Ha ocurrido un error, intentalo nuevamente.";
    public static final int UNAUTHORIZED_ERROR_CODE = 401;
    public static final int NOT_FOUND_ERROR_CODE = 404;
    public static final String MOVIES_ARRAY_LIST = "ArrayMovie";
    public static final String BILLBOARD_TOUR_GUIDE_KEY = "BillboardTourGuideKey";
    public static final String SESSION_TOUR_GUIDE_KEY = "SessionTourGuideKey";
    public static final String DETAIL_TOUR_GUIDE_KEY = "DetailTourGuideKey";
    public static final int TOUR_GUIDE_MADE = 1;
    public static final String EMPTY = "";
    public static final String PREFERENCES = "Preferences";

    public static final String IMAGE_KEY = "ImageKey";
    public static final String USER_KEY = "UserKey";
    public static final String LIKES_KEY = "LikesKey";
    public static final String FOLLOWING_KEY = "FollowingKey";
    public static final String FOLLOWER_KEY = "FollowerKey";
    public static final String CHANGE_BUTTON = "ChangeButton";
    public static final int BUTTON_TWITTER_VISIBLE = 0;
    public static final int BUTTON_LOGOUT_VISIBLE = 1;


    //Constants to capture or upload photos
    public static final int REQUEST_CODE_PERMISSION = 2;
    public static final int GALLERY_KITKAT = 19;
    public static final int GALLERY = 20;
    public static final String PREFIX_FILE = "JPEG_";
    public static final String FORMAT_DATE_FILE = "yyyyMMdd_HHmmss";
    public static final String SUFFIX_FILE_IMAGE = ".jpg";
    public static final int CAMERA_CAPTURE = 21;
    public static final String SUFFIX_LOCAL_FILE = "file:";
    public static final int FROM_CAMERA = 0;
    public static final int FROM_GALLERY = 1;


}
