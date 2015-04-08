package smart.liyinwang.jn.smarthome.core;

/**
 * Created by ajou on 2015-03-04.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import smart.liyinwang.jn.smarthome.R;
import smart.liyinwang.jn.smarthome.user.LoginActivity;

public class IntroActivity extends Activity {

    // intro picture image view
    private ImageView mImageView;

    // animation objects
    private Animation mFadeIn;
    private Animation mFadeOut;

    // picture source
    private Drawable mIntroPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mImageView = (ImageView)findViewById(R.id.intro_picture_image_view);

        init();
        setListener();
    }

    private void init() {
        initPicture();
        initAnimation();
        mImageView.setImageDrawable(mIntroPicture);
        mImageView.startAnimation(mFadeIn);
    }

    private void initPicture() {
        mIntroPicture = getResources().getDrawable(R.drawable.intro);
    }

    private void initAnimation() {
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.intro_fade_in);
        mFadeOut = AnimationUtils.loadAnimation(this, R.anim.intro_fade_out);
    }

    private void setListener() {
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(mFadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
