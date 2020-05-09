package com.koubou.kbviewer.app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.koubou.kbviewer.R;
import com.koubou.kbviewer.entity.Video;
import com.koubou.kbviewer.entity.VideoSource;
import com.koubou.kbviewer.util.TestSources;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoSourceFragment extends Fragment {

    Toolbar toolbar_main;

    RecyclerView mRecyclerview;
    BaseQuickAdapter videoSourcesAdapter;
    SwipeRefreshLayout swipe_container;

    TestSources sources;
    List<VideoSource> videoSources=new ArrayList<>();


    public VideoSourceFragment(Toolbar toolbar) {
        // Required empty public constructor
        toolbar_main=toolbar;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vedio_source, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
        initRecyclerview();
        initSwipeRefreshLayout();

        initData();
    }



    void initToolbar(){
        toolbar_main.setTitle("片源管理");
    }

    void initRecyclerview(){
        mRecyclerview=(RecyclerView) getActivity().findViewById(R.id.recyclerview_container);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        mRecyclerview.setLayoutManager(manager);
        videoSourcesAdapter=new BaseQuickAdapter<VideoSource, BaseViewHolder>(R.layout.item_videosource,videoSources) {
            @Override
            protected void convert(BaseViewHolder helper, VideoSource videoSource) {
                ImageView iv_icon=(ImageView)helper.getView(R.id.iv_icon);
                TextView tv_name=(TextView)helper.getView(R.id.tv_name);
                TextView tv_version=(TextView)helper.getView(R.id.tv_version);
                tv_name.setText(videoSource.getName());
                tv_version.setText(videoSource.getVersion());

                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getColor(videoSource.getName());

                TextDrawable drawable = TextDrawable.builder()
                        .buildRoundRect(videoSource.getName().charAt(0)+"", color, 10);
                iv_icon.setImageDrawable(drawable);

            }
        };
        mRecyclerview.setAdapter(videoSourcesAdapter);
    }

    void initData(){
        sources=new TestSources();
        videoSources.clear();
        videoSources.addAll(sources.getVideoSources());
        videoSourcesAdapter.notifyDataSetChanged();
    }

    void initSwipeRefreshLayout(){
        swipe_container=(SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_container);
        swipe_container.setColorSchemeResources(R.color.sakura,R.color.sakura);
    }
}
