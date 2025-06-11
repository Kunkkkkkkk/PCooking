package com.ruoyi.pda.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 敏感词过滤工具类
 * 
 * @author ruoyi
 */
public class SensitiveWordFilter {
    
    // 敏感词列表
    private static final List<String> SENSITIVE_WORDS = Arrays.asList(
        // 暴力相关
        "杀", "死", "血", "暴力", "打架", "斗殴", "枪", "刀", "炸弹", "爆炸", "恐怖", "袭击",
        "谋杀", "自杀", "杀人", "砍人", "捅人", "血腥", "残忍", "虐待", "酷刑",
        
        // 色情低俗
        "色情", "黄色", "裸体", "做爱", "性交", "强奸", "嫖娼", "卖淫", "三级片", "AV",
        "成人", "情色", "淫荡", "淫秽", "下体", "生殖器", "阴茎", "阴道", "乳房", "屁股",
        
        // 毒品相关
        "毒品", "吸毒", "贩毒", "海洛因", "冰毒", "摇头丸", "大麻", "可卡因", "鸦片", "吗啡",
        
        // 赌博相关
        "赌博", "赌场", "赌钱", "押注", "彩票", "老虎机", "百家乐", "轮盘",
        
        // 诈骗相关
        "诈骗", "骗钱", "传销", "洗钱", "非法集资", "高利贷", "套路贷", "网络诈骗", "电信诈骗",
        
        // 其他不良信息
        "邪教", "法轮功", "全能神", "迷信", "封建迷信", "算命", "风水", "占卜", "巫术",
        "仇恨", "歧视", "种族主义", "纳粹", "希特勒", "屠杀", "种族灭绝"
    );
    
    /**
     * 检查文本是否包含敏感词
     * 
     * @param content 要检查的文本内容
     * @return 包含的敏感词列表
     */
    public static List<String> findSensitiveWords(String content) {
        List<String> foundWords = new ArrayList<>();
        
        if (content == null || content.trim().isEmpty()) {
            return foundWords;
        }
        
        String lowerContent = content.toLowerCase();
        
        for (String word : SENSITIVE_WORDS) {
            if (lowerContent.contains(word.toLowerCase())) {
                foundWords.add(word);
            }
        }
        
        return foundWords;
    }
    
    /**
     * 检查文本是否包含敏感词
     * 
     * @param content 要检查的文本内容
     * @return 如果包含敏感词返回true，否则返回false
     */
    public static boolean containsSensitiveWords(String content) {
        return !findSensitiveWords(content).isEmpty();
    }
    
    /**
     * 替换敏感词为*号
     * 
     * @param content 要处理的文本内容
     * @return 替换敏感词后的文本
     */
    public static String replaceSensitiveWords(String content) {
        if (content == null || content.trim().isEmpty()) {
            return content;
        }
        
        String result = content;
        
        for (String word : SENSITIVE_WORDS) {
            if (result.contains(word)) {
                StringBuilder replacement = new StringBuilder();
                for (int i = 0; i < word.length(); i++) {
                    replacement.append("*");
                }
                result = result.replace(word, replacement.toString());
            }
        }
        
        return result;
    }
} 