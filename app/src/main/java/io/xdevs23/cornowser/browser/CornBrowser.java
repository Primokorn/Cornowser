package io.xdevs23.cornowser.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import org.xdevs23.android.app.XquidCompatActivity;
import org.xdevs23.config.ConfigUtils;
import org.xdevs23.debugUtils.StackTraceParser;
import org.xdevs23.ui.utils.BarColors;
import org.xwalk.core.XWalkView;

import io.xdevs23.cornowser.browser.browser.BrowserDefaults;
import io.xdevs23.cornowser.browser.browser.BrowserStorage;
import io.xdevs23.cornowser.browser.browser.xwalk.CrunchyWalkView;

public class CornBrowser extends XquidCompatActivity {

    public static CrunchyWalkView publicWebRender = null;

    public static RelativeLayout
            omnibox                 = null,
            publicWebRenderLayout   = null
            ;

    private static View staticView;
    private static Context staticContext;
    private static Activity staticActivity;
    private static Window staticWindow;

    private static BrowserStorage browserStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_corn);

        staticActivity  = this;
        staticContext   = this.getApplicationContext();
        staticView      = findViewById(R.id.corn_root_view);
        staticWindow    = this.getWindow();

        browserStorage = new BrowserStorage();

        publicWebRender         = new CrunchyWalkView(getContext(), getActivity());
        publicWebRenderLayout   = (RelativeLayout)  findViewById(R.id.webrender_layout);

        omnibox                 = (RelativeLayout)  findViewById(R.id.omnibox_layout);

        BarColors.enableBarColoring(staticWindow, R.color.colorPrimaryDark);

        publicWebRenderLayout.addView(publicWebRender);

        publicWebRender.load(BrowserDefaults.HOME_URL, null);
    }

    public static View getView() {
        return staticView;
    }

    public static Context getContext() {
        return staticContext;
    }

    public static Activity getActivity() {
        return staticActivity;
    }

    public static Window getStaticWindow() {
        return staticWindow;
    }

    public static BrowserStorage getBrowserStorage() {
        return browserStorage;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (publicWebRender != null) {
            publicWebRender.pauseTimers();
            publicWebRender.onHide();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (publicWebRender != null) {
            publicWebRender.resumeTimers();
            publicWebRender.onShow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (publicWebRender != null)
            publicWebRender.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (publicWebRender != null)
            publicWebRender.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (publicWebRender != null)
            publicWebRender.onNewIntent(intent);

    }
}