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
SET FLOW_NORMAL_RUN_MARKER = 'V1,V2';
UPDATE EASYFLOW_FLOW_DETAIL_REC
SET FLOW_NORMAL_RUN_MARKER = 'V2'
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
