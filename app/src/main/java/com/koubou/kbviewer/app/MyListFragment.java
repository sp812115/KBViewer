package com.koubou.kbviewer.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.koubou.kbviewer.R;
import com.koubou.kbviewer.entity.Video;
import com.koubou.kbviewer.util.TestSources;
import com.koubou.kbviewer.util.dpTansferUtil;

import java.security.MessageDigest;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment {

    Toolbar toolbar_main;

    RecyclerView mRecyclerview;

    TestSources sources;

    public MyListFragment(Toolbar toolbar) {
        // Required empty public constructor
        toolbar_main=toolbar;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initToolbar();
        init();

    }

    void initToolbar(){
        toolbar_main.setTitle("我的列表");
    }

    void init(){
        mRecyclerview=(RecyclerView) getActivity().findViewById(R.id.recyclerview_container);
        sources=new TestSources();

        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);

        mRecyclerview.setLayoutManager(manager);
        mRecyclerview.setAdapter(new BaseQuickAdapter<Video, BaseViewHolder>(R.layout.item_vedio, sources.getVideos()) {
            @Override
            protected void convert(BaseViewHolder helper, Video item) {
                helper.setText(R.id.tv_title, item.getTitle());

                WindowManager wm1 = getActivity().getWindowManager();
                int width1 = wm1.getDefaultDisplay().getWidth();
                int height1 = wm1.getDefaultDisplay().getHeight();
                ImageView iv_cover=  (ImageView)helper.getView(R.id.iv_cover);
                ViewGroup.LayoutParams params = iv_cover.getLayoutParams();
                params.height = ((width1 - dpTansferUtil.dip2px(getActivity(),30) )/6)*4;


                DrawableCrossFadeFactory drawableCrossFadeFactory
                        = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();
                Glide.with(getActivity())
                        .load(item.getCover())
                        .apply(RequestOptions
                                .bitmapTransform(new Transformation<Bitmap>() {
                                    @NonNull
                                    @Override
                                    public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
                                        return null;
                                    }

                                    @Override
                                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

                                    }
                                })
                                .centerCrop()
                                .format(PREFER_ARGB_8888))
                                //.bitmapTransform())
                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                        .into((ImageView) helper.getView(R.id.iv_cover));

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(getActivity(), VideoPlayActivity.class);
                        startActivity(intent);

                    }
                });
            }




        });



    }
}
