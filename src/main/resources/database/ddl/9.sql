CREATE TABLE `UserGroupSystemPermission` (
    TIMESTAMP_CREATED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
    , USERGROUP_ID INT NOT NULL
    , SYSTEMPERMISSION_ID  TINYINT NOT NULL
    , USER_ID_CREATED INT NOT NULL

    , PRIMARY KEY (USERGROUP_ID, SYSTEMPERMISSION_ID)
    , FOREIGN KEY (USERGROUP_ID)
            REFERENCES `UserGroup` (ID)
            ON DELETE CASCADE
    , FOREIGN KEY (SYSTEMPERMISSION_ID)
            REFERENCES `SystemPermission` (ID)
            ON DELETE CASCADE
    , FOREIGN KEY (USER_ID_CREATED)
            REFERENCES `User` (ID)
            ON DELETE RESTRICT
);