package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class PostBarWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addPostBar() {
        beginAt("/postBarform");
        assertTitleKeyMatches("postBarDetail.title");
        setTextField("ifAccessAllReply", "true");
        clickButton("save");
        assertTitleKeyMatches("postBarList.title");
        assertKeyPresent("postBar.added");
    }

    @Test
    public void listPostBars() {
        beginAt("/postBars");
        assertTitleKeyMatches("postBarList.title");

        // check that table is present
        assertTablePresent("postBarList");
    }

    @Test
    public void editPostBar() {
        beginAt("/postBarform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("postBarDetail.title");
    }

    @Test
    public void savePostBar() {
        beginAt("/postBarform?id=" + getInsertedId());
        assertTitleKeyMatches("postBarDetail.title");

        // update some of the required fields
        setTextField("ifAccessAllReply", "true");
        clickButton("save");
        assertTitleKeyMatches("postBarDetail.title");
        assertKeyPresent("postBar.updated");
    }

    @After
    public void removePostBar() {
        beginAt("/postBarform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("postBarList.title");
        assertKeyPresent("postBar.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/postBars");
        assertTablePresent("postBarList");
        Table table = getTable("postBarList");
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
