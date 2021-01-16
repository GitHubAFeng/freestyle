package com.afeng.web.module.app.event;

import com.afeng.web.framework.core.BaseEvent;
import com.afeng.web.module.app.model.AppUser;

public class AppUserLoginEvent extends BaseEvent<AppUser> {
    private static final long serialVersionUID = -4235801516280623532L;


    public AppUserLoginEvent(AppUser appUser) {
        super(appUser);
    }

    public AppUserLoginEvent(Object source, AppUser appUser) {
        super(source, appUser);
    }

}
