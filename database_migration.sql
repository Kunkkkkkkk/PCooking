-- 收藏夹功能数据库迁移脚本
-- 执行时间：需要在系统更新前执行

-- 1. 为收藏表添加收藏夹名称字段
ALTER TABLE master_social_collection 
ADD COLUMN folder_name VARCHAR(50) DEFAULT '默认' COMMENT '收藏夹名称';

-- 2. 为现有数据设置默认收藏夹名称（如果有数据的话）
UPDATE master_social_collection 
SET folder_name = '默认' 
WHERE folder_name IS NULL;

-- 3. 创建索引提高查询性能  
CREATE INDEX idx_user_folder ON master_social_collection(user_id, folder_name);
CREATE INDEX idx_user_social_folder ON master_social_collection(user_id, social_id, folder_name);

-- 4. 如果你需要删除唯一约束（如果之前有的话），可以执行以下命令
-- 注意：这个需要根据你的实际表结构来决定是否执行
-- ALTER TABLE master_social_collection DROP INDEX unique_user_social;

-- 5. 可选：如果你想要为每个用户创建一个默认收藏夹统计
-- 这个查询可以帮你检查当前的收藏夹分布情况
-- SELECT user_id, folder_name, COUNT(*) as count 
-- FROM master_social_collection 
-- GROUP BY user_id, folder_name 
-- ORDER BY user_id, folder_name; 