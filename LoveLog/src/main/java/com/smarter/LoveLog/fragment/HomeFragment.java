package com.smarter.LoveLog.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smarter.LoveLog.R;
import com.smarter.LoveLog.activity.ProductDeatil;
import com.smarter.LoveLog.adapter.Adapter_GridView;
import com.smarter.LoveLog.adapter.ImagePagerAdapter;
import com.smarter.LoveLog.ui.AutoScrollViewPager;
import com.smarter.LoveLog.ui.MyGridView;
import com.smarter.LoveLog.utills.DeviceUtil;
import com.smarter.LoveLog.utills.ListUtils;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/30.
 */
public class HomeFragment extends Fragment {
    protected WeakReference<View> mRootView;
    private View view;
    Context mContext;

    @Bind(R.id.rotate_header_list_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    //首页轮播
    private AutoScrollViewPager viewPager;
     /**首页轮播的界面的资源*/
    private List<Integer> imageIdList;
   ViewGroup viewgroup;
    /**存储首页轮播的界面*/
    private ImageView[] imageViews;

    //分类的九宫格
    private MyGridView gridView_classify;
    private Adapter_GridView adapter_GridView_classify;
        /* 分类九宫格的资源文件*/
    private int[] pic_path_classify = { R.mipmap.menu_guide_1, R.mipmap.menu_guide_2, R.mipmap.menu_guide_3, R.mipmap.menu_guide_4, R.mipmap.menu_guide_5, R.mipmap.menu_guide_6, R.mipmap.menu_guide_7, R.mipmap.menu_guide_8 };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            view = inflater.inflate(R.layout.home_fragment, null);
            mRootView = new WeakReference<View>(view);
            mContext=this.getContext();

            ButterKnife.bind(this, view);
            initFind();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        return mRootView.get();

    }

    private void initFind() {
        /**
         *
         */

        // header custom begin
        final StoreHouseHeader header = new StoreHouseHeader(mContext);
        header.setPadding(0, DeviceUtil.dp2px(mContext, 15), 0, 0);
        header.initWithString("Fine");
       mPtrFrame.setHeaderView(header);
       mPtrFrame.addPtrUIHandler(header);

        // 下拉刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
              Toast.makeText(mContext,"正在刷新",Toast.LENGTH_SHORT).show();
              // loadData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        /**
         * 轮播广告
         */
        viewPager = (AutoScrollViewPager) view.findViewById(R.id.viewPager_menu);
        viewgroup =(ViewGroup) view.findViewById(R.id.viewgroup);
        initViewPager();

        /**
         * 分类grid
         */
        gridView_classify = (MyGridView) view.findViewById(R.id.my_gridview);
        initGridView();

    }

    private void loadData() {
        mPtrFrame.refreshComplete();
    }

    private void initGridView() {
        gridView_classify.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter_GridView_classify = new Adapter_GridView(getActivity(), pic_path_classify);
        gridView_classify.setAdapter(adapter_GridView_classify);
        gridView_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //挑战到宝贝搜索界面
                Intent intent = new Intent(getActivity(), ProductDeatil.class);
                startActivity(intent);
            }
        });
    }

    private void  initViewPager(){


        imageIdList = new ArrayList<Integer>();

        imageIdList.add( R.mipmap.menu_viewpager_2);
        imageIdList.add( R.mipmap.menu_viewpager_3);
        imageIdList.add(R.mipmap.menu_viewpager_1);
        imageIdList.add(R.mipmap.menu_viewpager_4);
        imageIdList.add(R.mipmap.menu_viewpager_5 );
        viewPager.setAdapter(new ImagePagerAdapter(mContext, imageIdList).setInfiniteLoop(true));

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ListUtils.getSize(imageIdList));

        //创建小图像集合
        imageViews=new ImageView[imageIdList.size()];
        RelativeLayout.LayoutParams params;
        for(int i=0;i<imageViews.length;i++){

            //圆点之间的空白
            ImageView  kong = new ImageView(mContext);
            params = new RelativeLayout.LayoutParams(30,0);

            kong.setLayoutParams(params);
            kong.setBackgroundColor(Color.parseColor("#000000" +
                    ""));
            viewgroup.addView(kong);

            imageViews[i]=new ImageView(mContext);
            if(i==0){
                imageViews[i].setBackgroundResource(R.mipmap.play_display);
            }else{
                imageViews[i].setBackgroundResource(R.mipmap.play_hide);
            }


            viewgroup.addView(imageViews[i]);
        }
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());

    }
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {

            int positions=position % ListUtils.getSize(imageIdList);
            Log.d("HomeFragment",positions+">>");
           for(int i=0;i<imageViews.length;i++){

                if(positions==i){
                    imageViews[i].setBackgroundResource(R.mipmap.play_display);
                }else{
                    imageViews[i].setBackgroundResource(R.mipmap.play_hide);
                }

            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.startAutoScroll();
    }

    @Override
    public void onPause() {
        super.onPause();
        viewPager.stopAutoScroll();
    }
}
