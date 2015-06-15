package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class NewsTypeWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addNewsType() {
        beginAt("/newsTypeform");
        assertTitleKeyMatches("newsTypeDetail.title");
        clickButton("save");
        assertTitleKeyMatches("newsTypeList.title");
        assertKeyPresent("newsType.added");
    }

    @Test
    public void listNewsTypes() {
        beginAt("/newsTypes");
        assertTitleKeyMatches("newsTypeList.title");

        // check that table is present
        assertTablePresent("newsTypeList");
    }

    @Test
    public void editNewsType() {
        beginAt("/newsTypeform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("newsTypeDetail.title");
    }

    @Test
    public void saveNewsType() {
        beginAt("/newsTypeform?id=" + getInsertedId());
        assertTitleKeyMatches("newsTypeDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("newsTypeDetail.title");
        assertKeyPresent("newsType.updated");
    }

    @After
    public void removeNewsType() {
        beginAt("/newsTypeform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("newsTypeList.title");
        assertKeyPresent("newsType.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/newsTypes");
        assertTablePresent("newsTypeList");
        Table table = getTable("newsTypeList");
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
