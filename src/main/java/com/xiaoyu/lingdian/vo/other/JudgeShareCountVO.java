package com.xiaoyu.lingdian.vo.other;

/**
 * 判断用户当日分享次数是否超过配置信息
 * @author Administrator
 *
 */
public class JudgeShareCountVO {
    
    /**
     * 用户当日分享
     */
    private int nowShareCount;
    /**
     * 该等级所需人数
     */
    private int gradeShareCount;
    /**
     * 用户等级
     */
    private String  gradeName;
    

    public int getNowShareCount() {
        return nowShareCount;
    }
    public void setNowShareCount(int nowShareCount) {
        this.nowShareCount = nowShareCount;
    }
    public int getGradeShareCount() {
        return gradeShareCount;
    }
    public void setGradeShareCount(int gradeShareCount) {
        this.gradeShareCount = gradeShareCount;
    }
    public String getGradeName() {
        return gradeName;
    }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    public JudgeShareCountVO() { 
    }
    @Override
    public String toString() {
        return "JudgeShareCountVO [shareStatus=" 
                + ", nowShareCount=" + nowShareCount + ", gradeShareCount="
                + gradeShareCount + ", gradeName=" + gradeName + "]";
    }

   
    
    
}
