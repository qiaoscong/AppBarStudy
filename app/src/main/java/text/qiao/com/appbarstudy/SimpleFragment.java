package text.qiao.com.appbarstudy;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {

    private static final String ARG_SELECTION_NUM = "arg_selection_num"; // 参数的Tag

    // 显示的文本信息
    private static final int[] TEXTS = {
            R.string.tiffany_text,
            R.string.taeyeon_text,
            R.string.yoona_text
    };
    @BindView(R.id.main_tv_text)
    TextView mainTvText;
    @BindView(R.id.main_lv_list)
    ListView mainLvList;
    Unbinder unbinder;

    public SimpleFragment() {
        // Required empty public constructor
    }


    public static SimpleFragment newInstance(int selectionNum) {
        SimpleFragment simpleFragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SELECTION_NUM, selectionNum);
        simpleFragment.setArguments(args);
        return simpleFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainTvText.setText(TEXTS[getArguments().getInt(ARG_SELECTION_NUM)]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
