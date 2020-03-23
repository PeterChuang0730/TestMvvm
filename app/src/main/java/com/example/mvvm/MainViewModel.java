package com.example.mvvm;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    final MutableLiveData<String> mData = new MutableLiveData<>();
    final SingleLiveEvent<String> toastText = new SingleLiveEvent<>();
    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    private DataModel dataModel = new DataModel();

    void refresh() {
        isLoading.set(true);

        dataModel.retrieveData(new DataModel.onDataReadyCallback() {
            @Override
            public void onDataReady(String data) {
                mData.setValue(data);
                toastText.setValue("下載完成");
                isLoading.set(false);
            }
        });
    }
}
