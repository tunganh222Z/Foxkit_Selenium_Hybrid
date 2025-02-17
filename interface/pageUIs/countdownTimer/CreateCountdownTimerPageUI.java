package pageUIs.countdownTimer;

public class CreateCountdownTimerPageUI {
    public static final String ACTIVE_CHECKBOX = "xpath=//span[text()='Active']/parent::span/preceding-sibling::span//input";
    public static final String SCHEDULE_START_CHECKBOX = "xpath=//span[text()='Schedule a time to start the countdown']/parent::span/preceding-sibling::span//input";
    public static final String START_DATE_PICKER = "xpath=//label[text()='Pick the start time']//parent::div/parent::div/following-sibling::div//input";
    public static final String EXPIRE_DATE_PICKER = "xpath=//label[text()='Expires at']/parent::div/parent::div/following-sibling::div//input";
    public static final String TITLE_UNIT_TEXTBOX = "xpath=//h2[text()='Title']/parent::div/following-sibling::div//input";
    public static final String DAY_UNIT_TEXTBOX = "xpath=//label[text()='Day unit label']/parent::div/parent::div/following-sibling::div//input";
    public static final String HOUR_UNIT_TEXTBOX = "xpath=//label[text()='Hour unit label']/parent::div/parent::div/following-sibling::div//input";
    public static final String MINUTE_UNIT_TEXTBOX = "xpath=//label[text()='Minute unit label']/parent::div/parent::div/following-sibling::div//input";
    public static final String SECOND_UNIT_TEXTBOX = "xpath=//label[text()='Second unit label']/parent::div/parent::div/following-sibling::div//input";
    public static final String COUNTDOWN_TIMER_STATUS = "xpath=//div[@class='Polaris-Page-Header__TitleWrapper']//span[@class='Polaris-Badge Polaris-Badge--statusSuccess']/span[text()='Active']";
    public static final String HOUR_LABEL_IN_PREVIEW = "xpath=//div[@class='countdown-timer flex']/div[2]/span[2]";
    public static final String MINUTE_LABEL_IN_PREVIEW = "xpath=//div[@class='countdown-timer flex']/div[3]/span[2]";
    public static final String SECOND_LABEL_IN_PREVIEW = "xpath=//div[@class='countdown-timer flex']/div[4]/span[2]";
    public static final String BACK_ICON = "xpath=//nav[@role='navigation']//button";
    public static final String DAY_LABEL_IN_PREVIEW = "xpath=//div[@class='countdown-timer flex']/div[1]/span[2]";
}
