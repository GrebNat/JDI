package com.epam.jdi.uitests.testing.career.page_objects.site;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.preconditions.IPreconditions;
import com.epam.web.matcher.testng.Assert;
import com.google.common.base.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.alwaysMoveToCondition;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions.*;

/**
 * Created by Roman_Iovlev on 9/10/2016.
 */
public enum Preconditions implements IPreconditions {
    JOBS_LIST_SHOWN(
        () -> jobListingPage.verifyOpened() && !jobListingPage.jobsList.isEmpty(),
        () -> {
            jobListingPage.isOpened();
            Assert.isFalse(() -> jobListingPage.jobsList.isEmpty());
        }
    ),
    CAREERS_PAGE("/careers");

    public Supplier<Boolean> checkAction;
    public JAction moveToAction;

    Preconditions(Supplier<Boolean> checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }

    Preconditions(String uri) { this(() -> checkUrl(uri), () -> openUri(uri)); }

    public Boolean checkAction() { return checkAction.get();}

    public void moveToAction() { moveToAction.invoke(); }
}
