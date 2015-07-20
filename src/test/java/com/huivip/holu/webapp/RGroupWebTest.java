package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class RGroupWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addRGroup() {
        beginAt("/rGroupform");
        assertTitleKeyMatches("rGroupDetail.title");
        clickButton("save");
        assertTitleKeyMatches("rGroupList.title");
        assertKeyPresent("rGroup.added");
    }

    @Test
    public void listRGroups() {
        beginAt("/rGroups");
        assertTitleKeyMatches("rGroupList.title");

        // check that table is present
        assertTablePresent("rGroupList");
    }

    @Test
    public void editRGroup() {
        beginAt("/rGroupform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("rGroupDetail.title");
    }

    @Test
    public void saveRGroup() {
        beginAt("/rGroupform?id=" + getInsertedId());
        assertTitleKeyMatches("rGroupDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("rGroupDetail.title");
        assertKeyPresent("rGroup.updated");
    }

    @After
    public void removeRGroup() {
        beginAt("/rGroupform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("rGroupList.title");
        assertKeyPresent("rGroup.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/rGroups");
        assertTablePresent("rGroupList");
        Table table = getTable("rGroupList");
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
