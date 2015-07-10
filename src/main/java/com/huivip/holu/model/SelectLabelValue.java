package com.huivip.holu.model;

import java.io.Serializable;

/**
 * Created by sunlaihui on 7/10/15.
 */
public class SelectLabelValue implements Serializable {
    private String id;
    private String text;
    private boolean checked;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectLabelValue that = (SelectLabelValue) o;

        if (checked != that.checked) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        return !(icon != null ? !icon.equals(that.icon) : that.icon != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SelectLabelValue{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", checked=" + checked +
                ", icon='" + icon + '\'' +
                '}';
    }
}
