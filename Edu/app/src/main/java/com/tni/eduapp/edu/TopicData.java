package com.tni.eduapp.edu;

class TopicData {
    private String topic_id;
    private String topic_name;

    public TopicData() {}

    public TopicData(String topic_id, String topic_name)
    {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    public String getTopic_id()
    {
        return topic_id;
    }

    public String getTopic_name()
    {
        return topic_name;
    }

    public void setTopic_id(String topic_id)
    {
        this.topic_id = topic_id;
    }

    public void setTopic_name(String topic_name)
    {
        this.topic_name = topic_name;
    }
}