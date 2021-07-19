DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER
(
    USER_ID      BIGINT AUTO_INCREMENT COMMENT '使用者ID' PRIMARY KEY,
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
    ROLE_ID             BIGINT AUTO_INCREMENT COMMENT '角色ID' PRIMARY KEY,
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


DROP TABLE IF EXISTS SYS_POST;
CREATE TABLE SYS_POST
(
    POST_ID     BIGINT AUTO_INCREMENT COMMENT '崗位ID' PRIMARY KEY,
    POST_CODE   VARCHAR(64)            NOT NULL COMMENT '崗位編號',
    POST_NAME   VARCHAR(50)            NOT NULL COMMENT '崗位名稱',
    POST_SORT   INT(4)                 NOT NULL COMMENT '顯示順序',
    STATUS      CHAR                   NOT NULL COMMENT '狀態（0:正常, 1:停用）',
    CREATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '建立者',
    CREATE_TIME DATETIME               NULL COMMENT '建立時間',
    UPDATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME               NULL COMMENT '更新時間',
    REMARK      VARCHAR(500)           NULL COMMENT '備註'
)
    COMMENT '崗位訊息表';


DROP TABLE IF EXISTS SYS_USER_POST;
CREATE TABLE SYS_USER_POST
(
    USER_ID BIGINT NOT NULL COMMENT '使用者ID',
    POST_ID BIGINT NOT NULL COMMENT '崗位ID',
    PRIMARY KEY (USER_ID, POST_ID)
)
    COMMENT '使用者與崗位關連表';

DROP TABLE IF EXISTS SYS_USER_ROLE;
CREATE TABLE SYS_USER_ROLE
(
    USER_ID BIGINT NOT NULL COMMENT '使用者ID',
    ROLE_ID BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (USER_ID, ROLE_ID)
)
    COMMENT '使用者與角色關連表';



DROP TABLE IF EXISTS SYS_LOGININFO;
CREATE TABLE SYS_LOGININFO
(
    INFO_ID        BIGINT AUTO_INCREMENT COMMENT '訪問ID' PRIMARY KEY,
    USER_NAME      VARCHAR(50)  DEFAULT ''  NULL COMMENT '使用者帳號',
    IPADDR         VARCHAR(50)  DEFAULT ''  NULL COMMENT '登入IP',
    LOGIN_LOCATION VARCHAR(255) DEFAULT ''  NULL COMMENT '登入地點',
    BROWSER        VARCHAR(50)  DEFAULT ''  NULL COMMENT '瀏覽器類型',
    OS             VARCHAR(50)  DEFAULT ''  NULL COMMENT '作業系統',
    STATUS         CHAR         DEFAULT '0' NULL COMMENT '登入狀態（0:成功, 1:失敗）',
    MSG            VARCHAR(255) DEFAULT ''  NULL COMMENT '提示訊息',
    LOGIN_TIME     DATETIME                 NULL COMMENT '登入時間'
)
    COMMENT '系統訪問紀錄';

DROP TABLE IF EXISTS SYS_OPERATION_LOG;
CREATE TABLE SYS_OPERATION_LOG
(
    OPERATION_ID       BIGINT AUTO_INCREMENT COMMENT '行為日誌主鍵' PRIMARY KEY,
    TITLE              VARCHAR(50)   DEFAULT '' NULL COMMENT '模組標題',
    BUSINESS_TYPE      INT(2)        DEFAULT 0  NULL COMMENT '業務類型 (0:其它, 1:新增, 2:修改, 3:刪除, 4:授權, 5:匯出, 6:匯入, 7:強制登出, 8:產生程式碼, 9:清空資料)',
    METHOD             VARCHAR(100)  DEFAULT '' NULL COMMENT '方法名稱',
    REQUEST_METHOD     VARCHAR(10)   DEFAULT '' NULL COMMENT '請求方式',
    OPERATION_TYPE     INT(1)        DEFAULT 0  NULL COMMENT '行為類型 (0:其它, 1:後台使用者, 2:手機端使用者)',
    OPERATION_NAME     VARCHAR(50)   DEFAULT '' NULL COMMENT '操作人員',
    DEPT_NAME          VARCHAR(50)   DEFAULT '' NULL COMMENT '部門名稱',
    OPERATION_URL      VARCHAR(255)  DEFAULT '' NULL COMMENT '請求URL',
    OPERATION_IP       VARCHAR(50)   DEFAULT '' NULL COMMENT '訪問的IP',
    OPERATION_LOCATION VARCHAR(255)  DEFAULT '' NULL COMMENT '操作行為的地點',
    OPERATION_PARAM    VARCHAR(2000) DEFAULT '' NULL COMMENT '請求參數',
    JSON_RESULT        VARCHAR(2000) DEFAULT '' NULL COMMENT '返回的結果',
    STATUS             INT(1)        DEFAULT 0  NULL COMMENT '狀態 (0:正常, 1:異常)',
    ERROR_MSG          VARCHAR(2000) DEFAULT '' NULL COMMENT '錯誤訊息',
    OPERATION_TIME     DATETIME                 NULL COMMENT '操作時間'
)
    COMMENT '行為日誌紀錄';

