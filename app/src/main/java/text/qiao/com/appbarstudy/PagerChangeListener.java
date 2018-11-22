package text.qiao.com.appbarstudy;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

/**
 * @project：AppBarStudy
 * @fileName PagerChangeListener
 * @author：乔少聪
 * @date：2018/11/21 17:34
 * @describe：
 */

public class PagerChangeListener implements ViewPager.OnPageChangeListener {
    private ImageAnimator mImageAnimator;

    private boolean mIsScrolling = false;
    private int mFinalPostion;
    private int mCurrentPostion;

    public PagerChangeListener(ImageAnimator mImageAnimator) {
        this.mImageAnimator = mImageAnimator;
    }

    public static PagerChangeListener newInstance(SimpleAdapter simpleAdapter, ImageView mIvOutgoing, ImageView mIvTarget) {
        ImageAnimator imageAnimator = new ImageAnimator(mIvOutgoing, mIvTarget, simpleAdapter);
        return new PagerChangeListener(imageAnimator);
    }

    /**
     * 滑动监听
     *
     * @param position             当前位置
     * @param positionOffset       偏移距离[+-1]
     * @param positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LogUtils.e("position--" + position + "--positionOffset--" + positionOffset);
        if (isFinishScrolling(position, positionOffset)) {
            LogUtils.e("静止");
            finishScrolling(position);
        }
        if (isStartingScrollToPrevious(position, positionOffset)) {
            LogUtils.e("开始向前滚");
            startScrolling(position);
        } else if (isStartingScrollToNext(position, positionOffset)) {
            LogUtils.e("开始向后滚");
            startScrolling(position + 1);
        }
        if (ismIsScrollingToNext(position, positionOffset)) {
            LogUtils.e("向后划");
            mImageAnimator.forward(positionOffset);
        } else if (ismIsScrollingToPrevious(position, positionOffset)) {
            LogUtils.e("向前划");
            mImageAnimator.backward(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 终止动画
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private boolean isFinishScrolling(int position, float positionOffset) {
        return mIsScrolling  && (positionOffset == 0.0f && mFinalPostion == position)||(!mImageAnimator.isWithin(position));
    }


    /**
     * 从静止到开始滑动  ，下一个
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private boolean isStartingScrollToNext(int position, float positionOffset) {
        return !mIsScrolling && position == mCurrentPostion && positionOffset != 0.0f;
    }

    /**
     * 从静止到开始滑动  ，前一个
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private boolean isStartingScrollToPrevious(int position, float positionOffset) {
        return !mIsScrolling && position != mCurrentPostion && positionOffset != 0.0f;
    }


    /**
     * 开始滚动   ，向前
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private boolean ismIsScrollingToPrevious(int position, float positionOffset) {
        return mIsScrolling && position != mCurrentPostion && positionOffset != 0.0f;
    }

    /**
     * 开始滚动   ，向后
     *
     * @param position
     * @param positionOffset
     * @return
     */
    private boolean ismIsScrollingToNext(int position, float positionOffset) {
        return mIsScrolling && position == mCurrentPostion && positionOffset != 0.0f;
    }

    /**
     * 开始滚动
     *
     * @param position
     */
    private void startScrolling(int position) {
        mIsScrolling = true;
        mFinalPostion = position;
        mImageAnimator.start(mCurrentPostion, position);
    }

    private void finishScrolling(int position) {
        if (mIsScrolling) {
            mIsScrolling = false;
            mCurrentPostion = position;
            mImageAnimator.end(position);
        }

    }
}
