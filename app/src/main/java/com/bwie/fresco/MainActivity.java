package com.bwie.fresco;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.yuanjiao)
    Button yuanjiao;
    @BindView(R.id.yuanxing)
    Button yuanxing;
    @BindView(R.id.bili)
    Button bili;
    @BindView(R.id.jianjin)
    Button jianjin;
    @BindView(R.id.dongtu)
    Button dongtu;
    private SimpleDraweeView draweeView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        uri = Uri.parse("http://b-ssl.duitang.com/uploads/item/201508/30/20150830080620_niAmE.jpeg");
        draweeView = (SimpleDraweeView) findViewById(R.id.icon_image);
        draweeView.setImageURI(uri);
    }

    @OnClick({R.id.yuanjiao, R.id.yuanxing, R.id.bili, R.id.jianjin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yuanjiao:
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setCornersRadius(50f);
                draweeView.getHierarchy().setRoundingParams(roundingParams);
                break;
            case R.id.yuanxing:
                RoundingParams roundingParams1 = RoundingParams.fromCornersRadius(5f);
                roundingParams1.setRoundAsCircle(true);
                draweeView.getHierarchy().setRoundingParams(roundingParams1);
                break;
            case R.id.bili:
                draweeView.setAspectRatio(1.2f);
                break;
            case R.id.jianjin:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setTapToRetryEnabled(true)
                        .setOldController(draweeView.getController())
                        .build();
                draweeView.setController(controller);
                break;
        }
    }

    @OnClick(R.id.dongtu)
    public void onViewClicked() {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri("http://f.hiphotos.baidu.com/zhidao/pic/item/023b5bb5c9ea15ce330c869db6003af33b87b247.gif")
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(controller);

    }
}
