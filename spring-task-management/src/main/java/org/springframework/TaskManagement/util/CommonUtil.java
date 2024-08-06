package org.springframework.TaskManagement.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonUtil {

    public static boolean validateUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
