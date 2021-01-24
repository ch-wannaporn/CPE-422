package com.tni.eduapp.edu;

class TopicData {
    private String topic_id;
    private String topic_name;
    private String topic_video;

    public TopicData() {}

    public TopicData(String topic_id, String topic_name, String topic_video)
    {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
        this.topic_video = topic_video;
    }

    public String getTopic_id() { return topic_id; }

    public String getTopic_name()
    {
        return topic_name;
    }

    public String getTopic_video() { return topic_video; }

    public void setTopic_id(String topic_id)
    {
        this.topic_id = topic_id;
    }

    public void setTopic_name(String topic_name)
    {
        this.topic_name = topic_name;
    }

    public void setTopic_video(String topic_video) { this.topic_video = topic_video; }
}