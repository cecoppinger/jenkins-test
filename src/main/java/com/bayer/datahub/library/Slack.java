package com.bayer.datahub.library;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by glzmi on 8/14/19.
 */
public class Slack {
    private final String slackAlertWebHook;
    private final String slackMessageWebHook;
    private final String slackUser;

    public Slack(String slackAlertWebHook, String slackMessageWebHook, String slackUser) {
        this.slackAlertWebHook = slackAlertWebHook;
        this.slackMessageWebHook = slackMessageWebHook;
        this.slackUser = slackUser;
    }

    /**
     * Send info message to slack
     *  @param name    - Name of the message in slack
     * @param message - The actual message to send to slack channel
     */

    public void slackMessage(String name, String message) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SlackApi messageAPI = new SlackApi(this.slackMessageWebHook);
        messageAPI.call(new SlackMessage(name, format.format(new Date()) + " : " + message).setLinkNames(true));
    }

    /**
     * Send alert message to slack
     *
     * @param e - Exception to send to slack channel
     */

    public void slackAlert(Exception e) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SlackApi alertAPI = new SlackApi(this.slackAlertWebHook);
        alertAPI.call(new SlackMessage("Alert-", format.format(new Date()) + " : "
                + this.slackUser + " - " + e.toString() + "-" + e.getMessage() + ":" + e.getCause()).setLinkNames(true));
    }
}
