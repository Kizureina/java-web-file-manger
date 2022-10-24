package com.hit.pojo;

import java.util.Date;

/**
 * @author Yoruko
 */
public class Index {
    private String indexName;
    private Date editTime;
    private String subIndex;

    @Override
    public String toString() {
        return "Index{" +
                "indexName='" + indexName + '\'' +
                ", editTime=" + editTime +
                ", subIndex='" + subIndex + '\'' +
                '}';
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getSubIndex() {
        return subIndex;
    }

    public void setSubIndex(String subIndex) {
        this.subIndex = subIndex;
    }
}
