package com.koubou.kbviewer.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.koubou.kbviewer.R;
import com.koubou.kbviewer.entity.Episode;
import com.koubou.kbviewer.util.TestSources;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayActivity extends AppCompatActivity {

    TestSources sources;
    List<Episode> episodes=new ArrayList<>();

    //Recyclerview
    RecyclerView recyclerview_episodes;
    BaseQuickAdapter episodesAdapter;

    //videoplayer
    GSYVideoHelper gsyVideoHelper;
    GSYVideoHelper.GSYVideoHelperBuilder gsyVideoHelperBuilder;
    StandardGSYVideoPlayer videoPlayer;
    OrientationUtils orientationUtils;

    //记录当前播放index和播放列表选择的episodeitem(tv_title)
    int episodeIndex=0;
    TextView tv_title_playing=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio_play);
        initPlayer();
        initRecyclerview();

        //写法存疑
        initData();
        initEpisodelist();
    }




    private void initPlayer() {
        videoPlayer =  (StandardGSYVideoPlayer)findViewById(R.id.vedio_player);

        //动态设置videoPlayer高度  高宽比 9：16
        WindowManager wm1 = VideoPlayActivity.this.getWindowManager();
        int width1 = wm1.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params= videoPlayer.getLayoutParams();
        params.height=width1/16*9;

        //增加封面
//        ImageView imageView = new ImageView(this);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setImageResource(R.drawable.bg_drawer_header);
//        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //取消全屏播放动画效果
        videoPlayer.setShowFullAnimation(false);
        //设置全屏按键功能,这是使用的是旋转屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SourceLockedOrientationActivity")
            @Override
            public void onClick(View v) {
                //orientationUtils.resolveByClick(); 该方法运行效果不理想
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                videoPlayer.startWindowFullscreen(VideoPlayActivity.this, true, true);

            }
        });


        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initRecyclerview(){
        recyclerview_episodes=(RecyclerView)findViewById(R.id.recyclerview_episodes) ;
        LinearLayoutManager manager=new LinearLayoutManager(VideoPlayActivity.this,RecyclerView.VERTICAL,false);
        recyclerview_episodes.setLayoutManager(manager);
        episodesAdapter=new BaseQuickAdapter<Episode, BaseViewHolder>(R.layout.item_episode, episodes){
            @Override
            protected void convert(BaseViewHolder helper, Episode item) {
                TextView tv_title=(TextView)helper.getView(R.id.tv_title);
                helper.setText(R.id.tv_title,item.getTitle());
                tv_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playVedioEpisode(item);
                        episodeIndex=helper.getAdapterPosition();

                        if(tv_title_playing!=null){
                            tv_title_playing.setFocusable(false);
                            tv_title_playing.setFocusableInTouchMode(false);
                        }
                        tv_title_playing=tv_title;
                        tv_title_playing.setFocusable(true);
                        tv_title_playing.setFocusableInTouchMode(true);
                        tv_title_playing.requestFocus();

                    }
                });
            }



        };
        recyclerview_episodes.setAdapter(episodesAdapter);
    }

    void initData(){
        sources=new TestSources();
        episodes.clear();
        episodes.addAll(sources.getEpisodes());
        episodesAdapter.notifyDataSetChanged();
    }

    void initEpisodelist(){
        String vediosource = episodes.get(episodeIndex).getUrl();
        String title=episodes.get(episodeIndex).getTitle();
        videoPlayer.setUp(vediosource, true, title);
        recyclerview_episodes.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                TextView tv_title =   recyclerview_episodes.getLayoutManager().findViewByPosition(episodeIndex).findViewById(R.id.tv_title);
                tv_title_playing=tv_title;
                tv_title_playing.setFocusable(true);
                tv_title_playing.setFocusableInTouchMode(true);
                tv_title_playing.requestFocus();
                //OnGlobalLayoutListener可能会被多次触发
                //所以完成了需求后需要移除OnGlobalLayoutListener
                recyclerview_episodes.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void playVedioEpisode(Episode episode){
        videoPlayer.onVideoPause();
        String vediosource = episode.getUrl();
        String title=episode.getTitle();
        videoPlayer.setUp(vediosource, true, title);
        videoPlayer.startPlayLogic();

    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {

        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

}
