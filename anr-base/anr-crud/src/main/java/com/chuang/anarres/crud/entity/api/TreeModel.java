package com.chuang.anarres.crud.entity.api;

public interface TreeModel {

    Integer getId();
    Integer getParentId();
    void setParentId(Integer parentId);
    void setParents(String parents);
    String getParents();
    Integer getSortRank();
    void setSortRank(Integer sortRank);
}
