public class Session{
    private  int sessionID;
    private  String topic;
    private  String mentor;
    private  String department;
    private  String date;
    private  String time;
    private  String location;
    private  int maxParticipants;
    private  int currentParticipants = 0;
    public Session(int id,String t, String m, String d, String date, String time,String l, int max){
        this.sessionID = id;
        this.topic = t;
        this.mentor = m;
        this.department = d;
        this.date = date;
        this.time = time;
        this.location = l;
        this.maxParticipants = max;
    }
    // getters
    public int getSessionID(){return this.sessionID;}
    public String gettopic() {return this.topic;}
    public String getDate() { return this.date;}
    public String getMentor() {return this.mentor;}
    public String getDepartment() {return this.department;}
    public String getTime() {return this.time;}
    public String getLocation() {return this.location;}
    public int getCurrentParticipants() {return currentParticipants;}
    public int getMaxParticipants() {return maxParticipants;}

    // setters
    public void setTopic(String topic) {this.topic = topic;}
    public void setMentor(String mentor) {this.mentor = mentor;}
    public void setDepartment(String dept) {this.department = dept;}
    public void setDate(String date) {this.date = date;}
    public void setTime(String time) {this.time = time;}
    public void setLocation(String location) {this.location = location;}
    public void setMaxParticipants(int amt){this.maxParticipants = amt;}
    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }

    @Override
    public String toString() {
        int par = this.maxParticipants-this.currentParticipants;
        return "---Session " + this.sessionID  +
                "---\n Topic: " + this.topic  +
                ", Mentor: " + this.mentor +
                ", Department: " + this.department +
                "\nDate: " + this.date +
                ", Time: " + this.time +
                ", Location: " + this.location +
                ", Available seats: " + par + ((par < 0)?" (AVAILABILITY EXCEEDED)":"");
    }
}
