package thereisnospon.acclient.modules.submit.status;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thereisnospon.acclient.base.adapter.BasePullAdapter;
import thereisnospon.acclient.base.fragment.NormalPullFragment;
import thereisnospon.acclient.data.SubmmitStatus;

import thereisnospon.acclient.ui.adapter.SubmitStatusAdapter;


public class SubmitFragment extends NormalPullFragment<SubmmitStatus>

implements  SubmitStatusContact.View{

    SubmitStatusContact.Presenter presenter;

    SubmitQuery query;

    public static SubmitFragment newInstance(SubmitQuery query){
        SubmitFragment fragment=new SubmitFragment();
        fragment.query=query;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=normalView(inflater,container,savedInstanceState);
        presenter=new SubmitStatusPresenter(this);
        return view;
    }

    @Override
    public void onMoreStatus(List<SubmmitStatus> statusList) {
       notifyMoreData(statusList);

    }

    @Override
    public void onRefreshStatus(List<SubmmitStatus> statusList) {
      notifyRefreshData(statusList);
    }


    @Override
    public void onFailure(String err) {
        enableLoadMore(false);
        onRefreshCompleted();
    }

    @Override
    public BasePullAdapter<SubmmitStatus> createItemAdapter(List<SubmmitStatus> list) {
        return new SubmitStatusAdapter(list);
    }


    @Override
    public void loadMore() {
        presenter.moreStatus(query);
    }

    @Override
    public void refresh() {
        presenter.loadStatus(query);
    }
}
