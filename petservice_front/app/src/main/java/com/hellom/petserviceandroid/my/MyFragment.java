package com.hellom.petserviceandroid.my;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.hellom.petserviceandroid.R;
import com.hellom.petserviceandroid.base.Constant;
import com.hellom.petserviceandroid.base.LoadingCallBack;
import com.hellom.petserviceandroid.base.ResponseBean;
import com.hellom.petserviceandroid.buyvip.VipActivity;
import com.hellom.petserviceandroid.editinfo.EditInfoActivity;
import com.hellom.petserviceandroid.login.LoginActivity;
import com.hellom.petserviceandroid.login.LoginBean;
import com.hellom.petserviceandroid.orderlist.OrderListActivity;
import com.hellom.petserviceandroid.util.GlideUtil;
import com.hellom.petserviceandroid.util.SharePreferenceUtil;
import com.hellom.petserviceandroid.util.TakePhotoUtil;
import com.lzy.okgo.OkGo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import java.io.File;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends Fragment implements View.OnClickListener {
    private QMUIRadiusImageView petImg;
    private TextView petName;
    private QMUIGroupListView infoList;
    private Handler handler;
    private Uri targetUri;

    private QMUICommonListItemView petKind, petType, petAge, masterName, masterAddress, masterPhone;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        initListener();
        initData();
        return view;
    }

    private void initView(View view) {
        petImg = view.findViewById(R.id.pet_img);
        petName = view.findViewById(R.id.pet_name);
        infoList = view.findViewById(R.id.personal_info_list);
    }

    private void initListener() {
        petImg.setOnClickListener(this);
    }

    private void initData() {
        handler=new Handler();
        initPersonalInfo();
    }

    private void initPersonalInfo() {
        String imgUrl = SPUtils.getInstance().getString("petImg").replace("\\", "/");
        GlideUtil.load(getActivity(), imgUrl, petImg);
        petName.setText(String.format("%s%s", SPUtils.getInstance().getString("petName"), SPUtils.getInstance().getInt("vipId") == 0 ? "" : "-vip"));

        QMUICommonListItemView editInfo = infoList.createItemView("编辑资料");
        editInfo.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView orderList = infoList.createItemView("查看订单");
        orderList.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        QMUICommonListItemView vipList = infoList.createItemView("购买vip");
        vipList.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        petKind = infoList.createItemView(null, "宠物种类", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        petKind.setDetailText(SPUtils.getInstance().getString("petKind"));

        petType = infoList.createItemView(null, "宠物类型", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        petType.setDetailText(SPUtils.getInstance().getString("petType"));

        petAge = infoList.createItemView(null, "宠物年龄", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        petAge.setDetailText(String.valueOf(SPUtils.getInstance().getInt("petAge")));

        masterName = infoList.createItemView(null, "主人姓名", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        masterName.setDetailText(SPUtils.getInstance().getString("masterName"));

        masterAddress = infoList.createItemView(null, "主人地址", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        masterAddress.setDetailText(SPUtils.getInstance().getString("masterAddress"));

        masterPhone = infoList.createItemView(null, "主人手机", null, QMUICommonListItemView.HORIZONTAL, QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        masterPhone.setDetailText(SPUtils.getInstance().getString("masterPhone"));

        final QMUICommonListItemView logout = infoList.createItemView("退出登录");
        logout.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    String text = ((QMUICommonListItemView) v).getText().toString();
                    if ("编辑资料".equals(text)) {
                        jumpToEditInfo();
                    } else if ("退出登录".equals(text)) {
                        confirmLogout();
                    } else if ("查看订单".equals(text)) {
                        jumpToOrderList();
                    } else if ("购买vip".equals(text)) {
                        jumpToVipList();
                    }
                }
            }
        };

        QMUIGroupListView.newSection(getContext()).addItemView(editInfo, onClickListener).addItemView(orderList, onClickListener).addItemView(vipList, onClickListener)
                .addItemView(petKind, null).addItemView(petType, null).addItemView(petAge, null).addItemView(masterName, null).addItemView(masterAddress, null).addItemView(masterPhone, null).addItemView(logout, onClickListener).addTo(infoList);
    }

    private void jumpToEditInfo() {
        Intent intent = new Intent(getActivity(), EditInfoActivity.class);
        startActivityForResult(intent, 3);
    }

    private void jumpToOrderList() {
        Intent intent = new Intent(getActivity(), OrderListActivity.class);
        startActivity(intent);
    }

    private void jumpToVipList() {
        Intent intent = new Intent(getActivity(), VipActivity.class);
        startActivityForResult(intent, 4);
    }

    private void confirmLogout() {
        QMUIDialog dialog = new QMUIDialog.MessageDialogBuilder(getActivity()).setTitle("退出登录").setMessage("确定退出登录吗？").addAction("取消", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
            }
        }).addAction("确定", new QMUIDialogAction.ActionListener() {
            @Override
            public void onClick(QMUIDialog dialog, int index) {
                dialog.dismiss();
                logout();
            }
        }).create();
        dialog.show();
    }

    private void logout() {
        SharePreferenceUtil.logout();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private void refreshInfo() {
        OkGo.<LoginBean>post(Constant.ROOT_URL + Constant.GET_PERSONAL_INFO).params("id", SPUtils.getInstance().getInt("id")).execute(new LoadingCallBack<>(LoginBean.class, getActivity(), new LoadingCallBack.Callback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean response) {
                setNewInfo(response);
            }

            @Override
            public void onError() {

            }
        }, true));
    }

    private void setNewInfo(LoginBean loginBean) {
        LoginBean.MasterBean masterBean = loginBean.getMaster();
        String imgUrl = SPUtils.getInstance().getString("petImg").replace("\\", "/");
        GlideUtil.load(getActivity(), imgUrl, petImg);
        petName.setText(String.format("%s%s", masterBean.getPetName(), masterBean.getVipId() == 0 ? "" : "-vip"));
        petKind.setDetailText(masterBean.getPetKind());
        petType.setDetailText(masterBean.getPetType());
        petAge.setDetailText(String.valueOf(masterBean.getPetAge()));
        masterName.setDetailText(masterBean.getMasterName());
        masterAddress.setDetailText(masterBean.getMasterAddress());
        masterPhone.setDetailText(masterBean.getMasterPhone());

        SharePreferenceUtil.savePersonalInfo(loginBean);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pet_img:
                showTakePhotoList();
                break;
            default:
                break;
        }
    }

    private void showTakePhotoList() {
        new QMUIBottomSheet.BottomListSheetBuilder(getActivity()).addItem("相册").addItem("拍照").setOnSheetItemClickListener(new QMUIBottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
            @Override
            public void onClick(QMUIBottomSheet dialog, View itemView, int position, String tag) {
                dialog.dismiss();
                switch (position) {
                    case 0:
                        TakePhotoUtil.openGallery(MyFragment.this);
                        break;
                    case 1:
                        targetUri = TakePhotoUtil.openCamera(MyFragment.this);
                        break;
                    default:
                        break;
                }
            }
        }).build().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case TakePhotoUtil.TAKE_PHOTO_FROM_CAMERA:
                    updatePetImg();
                    break;
                case TakePhotoUtil.TAKE_PHOTO_FROM_GALLERY:
                    targetUri = data.getData();
                    updatePetImg();
                    break;
                case 3:
                    //更新信息
                    boolean updatePassword = data.getBooleanExtra("updatePassword", false);
                    refreshInfo();
                    if (updatePassword){
                        ToastUtils.showShort("您更改了密码，即将重新登录");
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                logout();
                            }
                        },1500);
                    }
                    break;
                case 4:
                    //购买vip
                    refreshInfo();
                    break;
                default:
                    break;
            }
        }
    }

    private void updatePetImg() {
        File file = UriUtils.uri2File(targetUri);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        Bitmap compressBitmap = ImageUtils.compressBySampleSize(bitmap, 2, true);
        FileUtils.createFileByDeleteOldFile(file);
        ImageUtils.save(compressBitmap, file, Bitmap.CompressFormat.JPEG);

        OkGo.<ResponseBean>post(Constant.ROOT_URL + Constant.UPDATE_PET_IMG)
                .params("id", SPUtils.getInstance().getInt("id"))
                .params("petImg", file)
                .execute(new LoadingCallBack<>(ResponseBean.class, getActivity(), new LoadingCallBack.Callback<ResponseBean>() {
                    @Override
                    public void onSuccess(ResponseBean response) {
                        ToastUtils.showShort("上传成功");
                        GlideUtil.load(getActivity(), targetUri, petImg);
                    }

                    @Override
                    public void onError() {

                    }
                }, true));
    }
}
