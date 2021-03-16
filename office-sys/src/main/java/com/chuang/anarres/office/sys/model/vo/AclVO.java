package com.chuang.anarres.office.sys.model.vo;

import com.chuang.anarres.office.sys.model.enums.ACLMode;
import lombok.Data;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class AclVO {
    private List<String> role;
    private List<String> ability;
    private ACLMode mode = ACLMode.allOf;
    private boolean except = false;


    public static AclVO of(@Nullable String role, String... ability) {
        AclVO vo = new AclVO();
        if(null == role) {
            vo.setRole(Collections.emptyList());
        }
        vo.setAbility(Arrays.asList(ability));
        return vo;
    }

}
