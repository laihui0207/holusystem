package com.huivip.holu.webapp;

import net.sourceforge.jwebunit.html.Row;
import net.sourceforge.jwebunit.html.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class CompanyWebTest {

    private ResourceBundle messages;

    @Before
    public void setUp() {
        setScriptingEnabled(false);
        getTestContext().setBaseUrl("http://" + System.getProperty("cargo.host") + ":" + System.getProperty("cargo.port"));
        getTestContext().setResourceBundleName("messages");
        messages = ResourceBundle.getBundle("messages");
    }

    @Before
    public void addCompany() {
        beginAt("/companyform");
        assertTitleKeyMatches("companyDetail.title");
        clickButton("save");
        assertTitleKeyMatches("companyList.title");
        assertKeyPresent("company.added");
    }

    @Test
    public void listCompanies() {
        beginAt("/companies");
        assertTitleKeyMatches("companyList.title");

        // check that table is present
        assertTablePresent("companyList");
    }

    @Test
    public void editCompany() {
        beginAt("/companyform?id=" + getInsertedId());
        clickButton("save");
        assertTitleKeyMatches("companyDetail.title");
    }

    @Test
    public void saveCompany() {
        beginAt("/companyform?id=" + getInsertedId());
        assertTitleKeyMatches("companyDetail.title");

        // update some of the required fields
        clickButton("save");
        assertTitleKeyMatches("companyDetail.title");
        assertKeyPresent("company.updated");
    }

    @After
    public void removeCompany() {
        beginAt("/companyform?id=" + getInsertedId());
        clickButton("delete");
        assertTitleKeyMatches("companyList.title");
        assertKeyPresent("company.deleted");
    }

    /**
     * Convenience method to get the id of the inserted record
     *
     * @return last id in the table
     */
    protected String getInsertedId() {
        beginAt("/companies");
        assertTablePresent("companyList");
        Table table = getTable("companyList");
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
