package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class PostWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addPost() {
        beginAt("/postform");
        assertTitleKeyMatches("postDetail.title");
        clickButton("save");
        assertTitleKeyMatches("postList.title");
        assertKeyPresent("post.added");
    }

    @Test
    public void listPosts() {
        beginAt("/posts");
        assertTitleKeyMatches("postList.title");

        // check that table is present
        assertTablePresent("postList");
    }

    @Test
    public void editPost() {
        beginAt("/postform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("postDetail.title");
    }

    @Test
    public void savePost() {
        beginAt("/postform?id=" + getInsertedId());
        assertTitleKeyMatches("postDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("postDetail.title");
        assertKeyPresent("post.updated");
    }

    @After
    public void removePost() {
        beginAt("/postform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("postList.title");
        assertKeyPresent("post.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/posts");
        assertTablePresent("postList");
        Table table = getTable("postList");
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
