package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class NewsWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addNews() {
        beginAt("/newsform");
        assertTitleKeyMatches("newsDetail.title");
        clickButton("save");
        assertTitleKeyMatches("newsList.title");
        assertKeyPresent("news.added");
    }

    @Test
    public void listNewss() {
        beginAt("/newss");
        assertTitleKeyMatches("newsList.title");

        // check that table is present
        assertTablePresent("newsList");
    }

    @Test
    public void editNews() {
        beginAt("/newsform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("newsDetail.title");
    }

    @Test
    public void saveNews() {
        beginAt("/newsform?id=" + getInsertedId());
        assertTitleKeyMatches("newsDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("newsDetail.title");
        assertKeyPresent("news.updated");
    }

    @After
    public void removeNews() {
        beginAt("/newsform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("newsList.title");
        assertKeyPresent("news.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/newss");
        assertTablePresent("newsList");
        Table table = getTable("newsList");
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
