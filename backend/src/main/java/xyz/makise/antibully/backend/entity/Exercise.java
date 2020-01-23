package xyz.makise.antibully.backend.entity;

public class Exercise {
    int exerciseId;
    String content;
    String answer;
/*
* 用于表示该题属于什么类型的题目
* 1. choice4表示是有4个选项的选择题
* 2.judge表示是非题
* */
    String type;
    String reason;

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
