package com.chuang.anarres.office.sys.model.bo;

import com.chuang.anarres.office.sys.model.enums.ACLMode;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class AclBO {
    private List<String> role;
    private List<String> ability;
    private ACLMode mode = ACLMode.allOf;
    private boolean except = false;

    public AclBO except() {
        AclBO bo;
        if (this.mode == ACLMode.allOf) {
             bo = allOf(this.role, this.ability);
        } else {
            bo = oneOf(this.role, this.ability);
        }
        bo.setExcept(!bo.isExcept());
        return bo;
    }

    public static AclBO allOf(String[] role, String[] ability) {
        return allOf(Arrays.asList(role), Arrays.asList(ability));
    }

    public static AclBO allOf(List<String> role, List<String> ability) {
        AclBO bo = new AclBO();
        bo.setRole(role);
        bo.setAbility(ability);
        bo.setMode(ACLMode.allOf);
        bo.setExcept(false);
        return bo;
    }

    public static AclBO oneOf(String[] role, String[] ability) {
        return oneOf(Arrays.asList(role), Arrays.asList(ability));
    }

    public static AclBO oneOf(List<String> role, List<String> ability) {
        AclBO bo = new AclBO();
        bo.setRole(role);
        bo.setAbility(ability);
        bo.setMode(ACLMode.oneOf);
        bo.setExcept(false);
        return bo;
    }

}
