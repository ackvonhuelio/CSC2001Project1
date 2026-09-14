
public class Main {
    public void main(String[] args){
        SessionList sessions = new SessionList();
        Session s1 = new Session(1, "", "","", "","","", 30);
        Session s2 = new Session(10, "", "","", "","","", 35);
        Session s3 = new Session(4, "", "","", "","","", 20);
        Session s4 = new Session(6, "", "joe","", "","","", 20);

        // add to beginning
        sessions.handleAdd(s1);
        // add to end
        sessions.handleAdd(s2);
        // add in the middle
        sessions.handleAdd(s3);
        // add in the middle
        sessions.handleAdd(s4);

        // null
        IO.println(sessions.grabById(2));
        // session 6
        IO.println(sessions.grabById(6));

        // increment session 6 participants
        sessions.registerParticipant(6);

        // search by mentor
        IO.println(sessions.search("joe",1,false));
        // search by ID and mentor
        IO.println(sessions.search("joe",1,true));

        // delete session
        sessions.removeById(6);

        // null
        IO.println(sessions.grabById(6));
    }
}
