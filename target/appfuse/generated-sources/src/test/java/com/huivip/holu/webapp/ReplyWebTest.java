package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ReplyWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addReply() {
        beginAt("/replyform");
        assertTitleKeyMatches("replyDetail.title");
        clickButton("save");
        assertTitleKeyMatches("replyList.title");
        assertKeyPresent("reply.added");
    }

    @Test
    public void listReplies() {
        beginAt("/replies");
        assertTitleKeyMatches("replyList.title");

        // check that table is present
        assertTablePresent("replyList");
    }

    @Test
    public void editReply() {
        beginAt("/replyform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("replyDetail.title");
    }

    @Test
    public void saveReply() {
        beginAt("/replyform?id=" + getInsertedId());
        assertTitleKeyMatches("replyDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("replyDetail.title");
        assertKeyPresent("reply.updated");
    }

    @After
    public void removeReply() {
        beginAt("/replyform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("replyList.title");
        assertKeyPresent("reply.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/replies");
        assertTablePresent("replyList");
        Table table = getTable("replyList");
        // Find link in last row, skip header row
        for (int i = 1; i < table.getRows().size(); i++) {
            Row row = table.getRows().get(i);
            if (i == table.getRowCount() - 1) {
                return row.getCells().get(0).getValue();
            }
        }
        return "";
    }

    private void assertTitleKeyMatches(String title) {
        assertTitleEquals(messages.getString(title) + " | " + messages.getString("webapp.name"));
    }
}
