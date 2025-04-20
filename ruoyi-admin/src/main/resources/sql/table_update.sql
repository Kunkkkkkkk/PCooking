-- 修改现有的master_order表结构
ALTER TABLE `master_order` 
  DROP COLUMN `dish_id`,
  DROP COLUMN `lack_material`,
  DROP COLUMN `quantity`;

-- 创建订单项表
CREATE TABLE `master_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `order_id` bigint NOT NULL COMMENT '订单ID',
  `dish_id` bigint NOT NULL COMMENT '菜品ID',
  `quantity` int NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '单价',
  `lack_material` varchar(255) DEFAULT NULL COMMENT '自备食材（逗号分隔的ID）',
  PRIMARY KEY (`id`),
  KEY `idx_order_id` (`order_id`) COMMENT '订单ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单项表'; 