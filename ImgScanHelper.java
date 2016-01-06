package hi.xiaoyu.hi_xiaoyu_dialog.ui;

import hi.xiaoyu.hi_xiaoyu_dialog.R;
import hi.xiaoyu.hi_xiaoyu_dialog.adapter.CommonPageAdapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class ImgScanHelper extends Dialog  {
	private static final String TAG = ImgScanHelper.class.getSimpleName();
	
	private List<String> mListImgUrls;
	private Integer[] mImgIds;
	private Context mContext;
	private ViewPager mViewPager;
	private int mClickItem;
	private GestureDetector mGestureDetector;
	

	public ImgScanHelper(Context context, Integer[] imgIds, int clickItem) {
		super(context, R.style.CustomDialog_fill);
		this.mContext = context;
		this.mImgIds = imgIds;
		this.mClickItem = clickItem;
		initView();
	}

	public ImgScanHelper(Context context, List<String> imgUrlss, int clickItem) {
		super(context, R.style.CustomDialog_fill);
		this.mContext = context;
		this.mListImgUrls = imgUrlss;
		this.mClickItem = clickItem;
		initView();
	}

	private void initView() {
		mViewPager = new ViewPager(mContext);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mViewPager.setLayoutParams(params);
		mViewPager.setBackgroundColor(0xFF000000);
		setContentView(mViewPager);
		setParams();
		initViewPager();
	}

	private void setParams() {
		LayoutParams lay = this.getWindow().getAttributes();
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
		Rect rect = new Rect();
		View view = getWindow().getDecorView();
		view.getWindowVisibleDisplayFrame(rect);
		lay.height = dm.heightPixels - rect.top;
		lay.width = dm.widthPixels;
	}

	private void initViewPager() {
		if (mImgIds != null && mImgIds.length > 0) {
			List<View> listImgs = new ArrayList<View>();
			for (int i = 0; i < mImgIds.length; i++) {
				TouchImageView iv = new TouchImageView(mContext);
				LayoutParams params = new LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				iv.setLayoutParams(params);
				listImgs.add(iv);
				//iv.setOnClickListener(this);
				iv.setOnDoubleTapListener(new OnDoubleTapListener() {
					
					@Override
					public boolean onSingleTapConfirmed(MotionEvent e) {
						return true;
					}
					
					@Override
					public boolean onDoubleTapEvent(MotionEvent e) {
						return true;
					}
					
					@Override
					public boolean onDoubleTap(MotionEvent e) {
						dismiss();
						return true;
					}
				});
				iv.setImageResource(mImgIds[i]);
				// 加载网络图片
				// BitmapHelper.getInstance(mContext).display(iv,
				// mListImgUrls.get(i));
			}
			if (listImgs.size() > 0) {
				CommonPageAdapter pageAdapter = new CommonPageAdapter(listImgs);
				mViewPager.setAdapter(pageAdapter);
				mViewPager.setCurrentItem(mClickItem);
			}
		}
	}
	
	@Override
	public void dismiss() {
		super.dismiss();

	}
	
}
