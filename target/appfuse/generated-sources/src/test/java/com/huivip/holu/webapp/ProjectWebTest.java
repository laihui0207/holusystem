package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class ProjectWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addProject() {
        beginAt("/projectform");
        assertTitleKeyMatches("projectDetail.title");
        clickButton("save");
        assertTitleKeyMatches("projectList.title");
        assertKeyPresent("project.added");
    }

    @Test
    public void listProjects() {
        beginAt("/projects");
        assertTitleKeyMatches("projectList.title");

        // check that table is present
        assertTablePresent("projectList");
    }

    @Test
    public void editProject() {
        beginAt("/projectform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("projectDetail.title");
    }

    @Test
    public void saveProject() {
        beginAt("/projectform?id=" + getInsertedId());
        assertTitleKeyMatches("projectDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("projectDetail.title");
        assertKeyPresent("project.updated");
    }

    @After
    public void removeProject() {
        beginAt("/projectform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("projectList.title");
        assertKeyPresent("project.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/projects");
        assertTablePresent("projectList");
        Table table = getTable("projectList");
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
