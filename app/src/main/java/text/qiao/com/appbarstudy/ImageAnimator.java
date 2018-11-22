package text.qiao.com.appbarstudy;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

/**
 * @project：AppBarStudy
 * @fileName ImageAnimator
 * @author：乔少聪
 * @date：2018/11/21 17:30
 * @describe： 渐变动画
 */

public class ImageAnimator {
    private final static float FACTOR = 0.1f;
    private final ImageView mIvOutgoing;
    private final ImageView mIvTarget;
    private final SimpleAdapter mSimpleAdapter;

    /**
     * 起始位置
     */
    private int mStartPostion;
    private int mMinPos;
    private int mMaxPos;

    public ImageAnimator(ImageView mIvOutgoing, ImageView mIvTarget, SimpleAdapter mSimpleAdapter) {
        this.mIvOutgoing = mIvOutgoing;
        this.mIvTarget = mIvTarget;
        this.mSimpleAdapter = mSimpleAdapter;
    }

    /**
     * 开始
     *
     * @param startPostion
     * @param endPostion
     */
    public void start(int startPostion, int endPostion) {
        mStartPostion = startPostion;

        //终止位置图片
        @DrawableRes int incomeId = mSimpleAdapter.getDrawable(endPostion);
        //原始图片
        mIvOutgoing.setImageDrawable(mIvTarget.getDrawable());
        mIvOutgoing.setTranslationX(0F);
        mIvOutgoing.setVisibility(View.VISIBLE);
        mIvOutgoing.setAlpha(1.0F);

        mIvTarget.setImageResource(incomeId);
        mMinPos = Math.min(startPostion, endPostion);
        mMaxPos = Math.max(startPostion, endPostion);
    }

    /**
     * 结束
     *
     * @param endPostion
     */
    public void end(int endPostion) {
        @DrawableRes int incomeId = mSimpleAdapter.getDrawable(endPostion);
        mIvTarget.setTranslationX(0F);

        if (endPostion == mStartPostion) {
            mIvTarget.setImageDrawable(mIvOutgoing.getDrawable());
        } else {
            mIvTarget.setImageResource(incomeId);
            mIvTarget.setVisibility(View.VISIBLE);
            mIvTarget.setAlpha(1.0F);
        }


    }

    /**
     * 向前滑动
     *
     * @param positionOffset
     */
    public void forward(float positionOffset) {
        int width = mIvTarget.getWidth();
//        LogUtils.e("forward--" + positionOffset+"--width--"+width);

//        mIvTarget.setTranslationX(0F);
        LogUtils.e("(1 - positionOffset) * (FACTOR * width)--" + (1 - positionOffset) * (FACTOR * width));
        mIvTarget.setTranslationX((1 - positionOffset) * (FACTOR * width));
        mIvOutgoing.setTranslationX(-positionOffset * (FACTOR * width));
        mIvTarget.setAlpha(positionOffset);

    }

    /**
     * 向后滑动
     *
     * @param positionOffset
     */
    public void backward(float positionOffset) {

        int width = mIvTarget.getWidth();
//        LogUtils.e("backward--" + positionOffset+"--width--"+width);
//        mIvTarget.setTranslationX(0.0F);
        LogUtils.e("(1 - positionOffset) * (FACTOR * width)--" + (1 - positionOffset) * (FACTOR * width));
        mIvOutgoing.setTranslationX((1 - positionOffset) * (FACTOR * width));
        mIvTarget.setTranslationX(-positionOffset * (FACTOR * width));

        mIvTarget.setAlpha(1 - positionOffset);
    }

    public boolean isWithin(int postion) {
//        LogUtils.e("isWithin:"+(postion >= mMinPos && postion <= mMaxPos));
        return postion >= mMinPos && postion <= mMaxPos;
    }

}
