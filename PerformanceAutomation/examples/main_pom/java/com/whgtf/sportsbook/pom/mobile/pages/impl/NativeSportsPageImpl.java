package com.whgtf.sportsbook.pom.mobile.pages.impl;

import com.whgtf.sportsbook.pom.common.pages.abstracts.AbstractPageObject;
import com.whgtf.sportsbook.pom.mobile.components.impl.*;
import com.whgtf.sportsbook.pom.mobile.components.interfaces.*;
import com.whgtf.sportsbook.pom.mobile.pages.inerfaces.NativeSportsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy
public class NativeSportsPageImpl extends AbstractPageObject implements NativeSportsPage {

    @Autowired
    @Lazy
    private NativeHeaderComponent nativeHeaderComponent;

    @Autowired
    @Lazy
    private NativeSideMenuComponent nativeSideMenuComponent;

    @Autowired
    @Lazy
    private NativeElementsComponent nativeElementsComponent;

    @Autowired
    @Lazy
    private NativeLoginComponent nativeLoginComponent;


    @Override
    public NativeHeaderComponent getHeader() {
        return new NativeHeaderComponentImpl();
    }

    @Override
    public NativeSideMenuComponent getSideMenu() {
        return new NativeSideMenuComponentImpl();
    }

    @Override
    public NativeElementsComponent getNativeElements() {
        return new NativeElementsComponentImpl();
    }

    @Override
    public NativeLoginComponent getLoginComponent() {
        return new NativeLoginComponentImpl();
    }

    @Override
    public NativeFirstTimeComponent getFirstTimeComponent() {
        return new NativeFirstTimeComponentImpl();
    }
}
