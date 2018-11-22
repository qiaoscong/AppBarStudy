package text.qiao.com.appbarstudy;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @project：AppBarStudy
 * @fileName SimpleAdapter
 * @author：乔少聪
 * @date：2018/11/21 15:03
 * @describe：
 */

public class SimpleAdapter extends FragmentPagerAdapter {

    private static final Section[] SECTIONS = {
            new Section("黄美英", R.drawable.tiffany),
            new Section("金泰妍", R.drawable.taeyeon),
            new Section("林允儿", R.drawable.yoona)};

    public SimpleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SimpleFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return SECTIONS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position>=0&&position<SECTIONS.length){
            return SECTIONS[position].getmTitle();
        }
        return null;
    }
    // 图片接口
    public @DrawableRes
    int getDrawable(int position) {
        if (position >= 0 && position < SECTIONS.length) {
            return SECTIONS[position].getmDrawable();
        }
        return -1;
    }
    private final static class Section {
        private final String mTitle;
        private final @DrawableRes int mDrawable; // 图片

        public Section(String mTitle, int mDrawable) {
            this.mTitle = mTitle;
            this.mDrawable = mDrawable;
        }

        public String getmTitle() {
            return mTitle;
        }

        public int getmDrawable() {
            return mDrawable;
        }
    }
}
