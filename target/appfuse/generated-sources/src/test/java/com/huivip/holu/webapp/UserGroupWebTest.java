package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class UserGroupWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addUserGroup() {
        beginAt("/userGroupform");
        assertTitleKeyMatches("userGroupDetail.title");
        clickButton("save");
        assertTitleKeyMatches("userGroupList.title");
        assertKeyPresent("userGroup.added");
    }

    @Test
    public void listUserGroups() {
        beginAt("/userGroups");
        assertTitleKeyMatches("userGroupList.title");

        // check that table is present
        assertTablePresent("userGroupList");
    }

    @Test
    public void editUserGroup() {
        beginAt("/userGroupform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("userGroupDetail.title");
    }

    @Test
    public void saveUserGroup() {
        beginAt("/userGroupform?id=" + getInsertedId());
        assertTitleKeyMatches("userGroupDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("userGroupDetail.title");
        assertKeyPresent("userGroup.updated");
    }

    @After
    public void removeUserGroup() {
        beginAt("/userGroupform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("userGroupList.title");
        assertKeyPresent("userGroup.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/userGroups");
        assertTablePresent("userGroupList");
        Table table = getTable("userGroupList");
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
