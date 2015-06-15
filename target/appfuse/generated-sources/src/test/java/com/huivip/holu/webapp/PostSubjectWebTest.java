package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class PostSubjectWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addPostSubject() {
        beginAt("/postSubjectform");
        assertTitleKeyMatches("postSubjectDetail.title");
        clickButton("save");
        assertTitleKeyMatches("postSubjectList.title");
        assertKeyPresent("postSubject.added");
    }

    @Test
    public void listPostSubjects() {
        beginAt("/postSubjects");
        assertTitleKeyMatches("postSubjectList.title");

        // check that table is present
        assertTablePresent("postSubjectList");
    }

    @Test
    public void editPostSubject() {
        beginAt("/postSubjectform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("postSubjectDetail.title");
    }

    @Test
    public void savePostSubject() {
        beginAt("/postSubjectform?id=" + getInsertedId());
        assertTitleKeyMatches("postSubjectDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("postSubjectDetail.title");
        assertKeyPresent("postSubject.updated");
    }

    @After
    public void removePostSubject() {
        beginAt("/postSubjectform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("postSubjectList.title");
        assertKeyPresent("postSubject.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/postSubjects");
        assertTablePresent("postSubjectList");
        Table table = getTable("postSubjectList");
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
