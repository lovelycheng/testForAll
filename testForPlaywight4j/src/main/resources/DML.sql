-- ----------------------------
-- 更新时间：2021/12/22
-- 负责人：李杰
-- 更新内容：项目,环境,集群id 关联变更

-- cluster_id 为空时数据填充无效集群id DML
UPDATE "EASYFLOW_3052TJBHDEV".EASYFLOW_ENV
SET CLUSTER_ID=CONCAT('empty-', substr(cast(dbms_random.value as varchar2(38)), 3, 20))
WHERE CLUSTER_ID = ''
   OR CLUSTER_ID IS NULL;


/**
 * 1.2.3.4 DML
 * */

-- ----------------------------
-- 更新时间：2022/01/21
-- 负责人：郑振强
-- 更新内容：接口业务类型赋默认值
-- ----------------------------

UPDATE "EASYFLOW_3052TJBHDEV"."EASYFLOW_API_ROUTE_CONFIG"
SET "API_BIZ_TYPE" = '01' WHERE API_BIZ_TYPE IS NULL ;




/**
 * 1.2.3.5 DML
 * */

-- ----------------------------
-- 更新时间：2021/12/17
-- 负责人：李仁会
-- 更新内容：设置实例清理时间功能涉及表修改
-- ----------------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '042';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('042', '001301', '设置实例清理时间', 'SET_FLOW_INSTANCE_CLEAR_TIME', '流程权限', '设置实例清理时间');

DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_PROJECT_USER_ROLE_REL" WHERE (PROJECT_CODE, USER_ID, ROLE_ID)
    IN
                                                              (SELECT PROJECT_CODE, USER_ID, ROLE_ID FROM (SELECT PROJECT_CODE, USER_ID, ROLE_ID FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_PROJECT_USER_ROLE_REL" GROUP BY PROJECT_CODE, USER_ID, ROLE_ID HAVING COUNT(*)>1) S1)
                                                          AND
        USER_ROLE_REL_ID NOT IN (SELECT USER_ROLE_REL_ID FROM (SELECT MAX(USER_ROLE_REL_ID) USER_ROLE_REL_ID,PROJECT_CODE, USER_ID, ROLE_ID FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_PROJECT_USER_ROLE_REL" GROUP BY PROJECT_CODE, USER_ID, ROLE_ID HAVING COUNT(*)>1) S2);

UPDATE "EASYFLOW_3052TJBHDEV"."EASYFLOW_FLOW_DEFINE"
SET BIZ_TYPE = '01'
WHERE BIZ_TYPE = ''
   OR BIZ_TYPE IS NULL

-- ----------------------------
-- 更新时间：2022/01/04
-- 负责人：李仁会
-- 更新内容：流程编排灰度功能涉及表修改
-- ----------------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '043';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('043', '001401', '修改发布计划', 'MODIFY_RELEASE_PLAN', '流程权限', '修改发布计划');

-- -------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '044';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('044', '001402', '发布发布计划', 'ACTIVATE_RELEASE_PLAN', '流程权限', '发布发布计划');

-- -------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '045';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('045', '001403', '升级发布计划', 'UPGRADE_RELEASE_PLAN', '流程权限', '升级发布计划');

-- -------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '046';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('046', '001404', '环境配置', 'ENV_CONFIG', '流程权限', '环境配置');

-- -------------------
DELETE FROM "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH" WHERE AUTH_ID = '047';
INSERT INTO "EASYFLOW_3052TJBHDEV"."EASYFLOW_AUTH"("AUTH_ID", "AUTH_NO", "NAME", "CODE", "GROUP_NAME", "DISCRIPTION")
VALUES ('047', '001405', '停机动作', 'SHUTDOWN_ACTION', '流程权限', '停机动作');



/**
 * 1.2.3.5.1 DML
 *
 * */

-- ----------------------------
-- 更新时间：2022/03/15
-- 负责人：蔡锦堂
-- 更新内容：软删除字段设置默认值
-- ----------------------------
UPDATE EASYFLOW_FLOW_DEFINE
SET DELETED = 0;
UPDATE EASYFLOW_FLOW_DETAIL_REC
SET DELETED = 0;
UPDATE EASYFLOW_FLOW_RELEASE_REC
SET DELETED = 0;
UPDATE EASYFLOW_FLOW_RELATION
SET DELETED = 0;
UPDATE EASYFLOW_FLOW_ENV_RELATION
SET DELETED = 0;
UPDATE EASYFLOW_FLOW_TASK
SET DELETED = 0;
UPDATE EASYFLOW_TASK
SET DELETED = 0;

UPDATE EASYFLOW_API_ROUTE_ENV_REL
SET DELETED = 0;
UPDATE EASYFLOW_API_HTTP_CONFIG
SET DELETED = 0;
UPDATE EASYFLOW_API_ROUTE_CONFIG
SET DELETED = 0;

UPDATE EASYFLOW_COMP_TEMPLATE_BASE
SET DELETED = 0;
UPDATE EASYFLOW_RULE_DEFINE
SET DELETED = 0;
UPDATE EASYFLOW_RULE_FUNCTION
SET DELETED = 0;

UPDATE EASYFLOW_PROJECT
SET DELETED = 0;
UPDATE EASYFLOW_PROJECT_ROLE
SET DELETED = 0;
UPDATE EASYFLOW_PROJECT_USER
SET DELETED = 0;
UPDATE EASYFLOW_PROJECT_USER_ROLE_REL
SET DELETED = 0;
UPDATE EASYFLOW_DIRECTORY
SET DELETED = 0;
UPDATE EASYFLOW_ENV
SET DELETED = 0;
UPDATE EASYFLOW_PROJECT_DEPEND
SET DELETED = 0;


/**
 * 1.2.3.6 DML
 * */
-- ----------------------------
-- 更新时间：2022-03-19
-- 负责人：蔡锦堂
-- 更新内容：流程定义表新增图类型
-- ----------------------------
UPDATE EASYFLOW_FLOW_DEFINE
SET FLOW_DEFINE_TYPE = '01';
-- ----------------------------
-- 更新时间：2022-03-30
-- 负责人：蔡锦堂
-- 更新内容：流程版本表新增执行版本字段
-- ----------------------------
UPDATE EASYFLOW_FLOW_DETAIL_REC
SET FLOW_NORMAL_RUN_MARKER = "V1,V2";
UPDATE EASYFLOW_FLOW_DETAIL_REC
SET FLOW_NORMAL_RUN_MARKER = "V2"
WHERE FLOW_ID IN (
    SELECT FLOW_ID
    FROM EASYFLOW_FLOW_DEFINE
    WHERE FLOW_DEFINE_TYPE = '02'
    );

-- ----------------------------
-- 更新时间：2022-04-02
-- 负责人：李仁会
-- 更新内容：流程编排业务组件表新增tags字段
-- ----------------------------
UPDATE EASYFLOW_COMPONENT
SET TAGS = '[".0.ALL/1.NONE."]';

COMMIT;
