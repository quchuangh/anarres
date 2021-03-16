package com.chuang.anarres.office.sys.entity.api;

public interface TreeModel {

    Integer getId();
    Integer getParentId();
    void setParentId(Integer parentId);
    void setParents(String parents);
    String getParents();
    Integer getSortRank();
    void setSortRank(Integer sortRank);
}
