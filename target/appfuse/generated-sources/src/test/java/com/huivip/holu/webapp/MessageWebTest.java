package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class MessageWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addMessage() {
        beginAt("/messageform");
        assertTitleKeyMatches("messageDetail.title");
        setTextField("status", "1611054047");
        clickButton("save");
        assertTitleKeyMatches("messageList.title");
        assertKeyPresent("message.added");
    }

    @Test
    public void listMessages() {
        beginAt("/messages");
        assertTitleKeyMatches("messageList.title");

        // check that table is present
        assertTablePresent("messageList");
    }

    @Test
    public void editMessage() {
        beginAt("/messageform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("messageDetail.title");
    }

    @Test
    public void saveMessage() {
        beginAt("/messageform?id=" + getInsertedId());
        assertTitleKeyMatches("messageDetail.title");

        // update some of the required fields
        setTextField("status", "1705553889");
        clickButton("save");
        assertTitleKeyMatches("messageDetail.title");
        assertKeyPresent("message.updated");
    }

    @After
    public void removeMessage() {
        beginAt("/messageform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("messageList.title");
        assertKeyPresent("message.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/messages");
        assertTablePresent("messageList");
        Table table = getTable("messageList");
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
