DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER
(
    USER_ID      BIGINT AUTO_INCREMENT COMMENT '使用者ID'
        PRIMARY KEY,
    DEPT_ID      BIGINT                    NULL COMMENT '部門ID',
    USER_NAME    VARCHAR(30)               NOT NULL COMMENT '使用者帳號',
    NICK_NAME    VARCHAR(30)               NOT NULL COMMENT '使用者暱稱',
    USER_TYPE    VARCHAR(2)   DEFAULT '00' NULL COMMENT '使用者類型（00系統使用者）',
    EMAIL        VARCHAR(50)  DEFAULT ''   NULL COMMENT '使用者信箱',
    PHONE_NUMBER VARCHAR(10)  DEFAULT ''   NULL COMMENT '手機號碼',
    GENDER       CHAR         DEFAULT '1'  NULL COMMENT '使用者性別（0女, 1男, 2未知）',
    AVATAR       VARCHAR(100) DEFAULT ''   NULL COMMENT '大頭照圖片位置',
    PASSWORD     VARCHAR(100) DEFAULT ''   NULL COMMENT '密碼',
    STATUS       CHAR         DEFAULT '0'  NULL COMMENT '帳號狀態（0正常, 1停用）',
    DEL_FLAG     CHAR         DEFAULT '0'  NULL COMMENT '刪除標誌（0代表存在, 2代表刪除）',
    LOGIN_IP     VARCHAR(50)  DEFAULT ''   NULL COMMENT '最後登入IP',
    LOGIN_DATE   DATETIME                  NULL COMMENT '最後登入時間',
    CREATE_BY    VARCHAR(64)  DEFAULT ''   NULL COMMENT '建立者',
    CREATE_TIME  DATETIME                  NULL COMMENT '建立時間',
    UPDATE_BY    VARCHAR(64)  DEFAULT ''   NULL COMMENT '更新者',
    UPDATE_TIME  DATETIME                  NULL COMMENT '更新時間',
    REMARK       VARCHAR(500)              NULL COMMENT '備註'
)
    COMMENT '關於使用者資訊';


DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE
(
    ROLE_ID             BIGINT AUTO_INCREMENT COMMENT '角色ID'
        PRIMARY KEY,
    ROLE_NAME           VARCHAR(30)             NOT NULL COMMENT '角色名稱',
    ROLE_KEY            VARCHAR(100)            NOT NULL COMMENT '角色權限字串',
    ROLE_SORT           INT(4)                  NOT NULL COMMENT '顯示順序',
    DATA_SCOPE          CHAR        DEFAULT '1' NULL COMMENT '資料可視範圍（1：全部資料權限2：自訂資料權限 3：該部門資料權限 4：該部門及以下資料權限）',
    MENU_CHECK_STRICTLY TINYINT(1)  DEFAULT 1   NULL COMMENT '選單樹選擇項是否關連顯示',
    DEPT_CHECK_STRICTLY TINYINT(1)  DEFAULT 1   NULL COMMENT '部門樹選擇項是否關連顯示',
    STATUS              CHAR                    NOT NULL COMMENT '角色狀態（0正常, 1停用）',
    DEL_FLAG            CHAR        DEFAULT '0' NULL COMMENT '刪除標誌（0存在, 2刪除）',
    CREATE_BY           VARCHAR(64) DEFAULT ''  NULL COMMENT '建立者',
    CREATE_TIME         DATETIME                NULL COMMENT '建立時間',
    UPDATE_BY           VARCHAR(64) DEFAULT ''  NULL COMMENT '更新者',
    UPDATE_TIME         DATETIME                NULL COMMENT '更新時間',
    REMARK              VARCHAR(500)            NULL COMMENT '備註'
)
    COMMENT '系統角色表';