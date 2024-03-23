package com.example.demo.utils.constants;

public class Roles {

    public static enum UserRoles {
        ROLE_STUDENT("STUDENT"),
        ROLE_ADMIN("ADMIN");

        private final String stringValue;

        UserRoles(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getValue() {
            return stringValue;
        }
    }
}
