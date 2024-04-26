package org.pftest.constants;

import org.pftest.helpers.Helpers;
import org.pftest.helpers.PropertiesHelpers;
import org.pftest.utils.ReportUtils;

import java.io.File;

public class FrameworkConstants {
    private FrameworkConstants() {
        super();
    }

    static {
        PropertiesHelpers.loadAllFiles();
    }

    public static final String PROJECT_PATH = Helpers.getCurrentDir();
    public static final String APP_IFRAME = PropertiesHelpers.getValue("APP_IFRAME");

    public static final int WAIT_DEFAULT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_DEFAULT"));
    public static final int WAIT_IMPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(PropertiesHelpers.getValue("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelpers.getValue("WAIT_PAGE_LOADED"));
    public static final int WAIT_SLEEP_STEP = Integer.parseInt(PropertiesHelpers.getValue("WAIT_SLEEP_STEP"));
    public static final String ACTIVE_PAGE_LOADED = PropertiesHelpers.getValue("ACTIVE_PAGE_LOADED");
    public static final String BROWSER = PropertiesHelpers.getValue("BROWSER");

    public static final String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static final String HEADLESS = PropertiesHelpers.getValue("HEADLESS");
    public static final String OVERRIDE_REPORTS = PropertiesHelpers.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelpers.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public static final String SCREENSHOT_PASSED_STEPS = PropertiesHelpers.getValue("SCREENSHOT_PASSED_STEPS");
    public static final String SCREENSHOT_FAILED_STEPS = PropertiesHelpers.getValue("SCREENSHOT_FAILED_STEPS");
    public static final String SCREENSHOT_SKIPPED_STEPS = PropertiesHelpers.getValue("SCREENSHOT_SKIPPED_STEPS");
    public static final String SCREENSHOT_ALL_STEPS = PropertiesHelpers.getValue("SCREENSHOT_ALL_STEPS");

    public static final String REPORT_TITLE = PropertiesHelpers.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelpers.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXTENT_REPORT_NAME = PropertiesHelpers.getValue("EXTENT_REPORT_NAME");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelpers.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelpers.getValue("EXPORT_CAPTURE_PATH");
    public static final String VIDEO_RECORD = PropertiesHelpers.getValue("VIDEO_RECORD");

    public static final String USER_DATA_DIR = PropertiesHelpers.getValue("USER_DATA_DIR");
    public static final String PROFILE_DIRECTORY = PropertiesHelpers.getValue("PROFILE_DIRECTORY");
    public static final String BROWSER_BINARY = PropertiesHelpers.getValue("BROWSER_BINARY");

    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
    public static String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;
    public static String getExtentReportFilePath() {
        if (EXTENT_REPORT_FILE_PATH.isEmpty()) {
            EXTENT_REPORT_FILE_PATH = ReportUtils.createExtentReportPath();
        }
        return EXTENT_REPORT_FILE_PATH;
    }
}
