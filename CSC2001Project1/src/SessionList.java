public class SessionList {
    SessionLink head = null;

    // constructors
    public SessionList() {
    }
    public SessionList(SessionLink h){
        this.head = h;
    }

    // get the Session by ID
    public Session grabById(int id){
        SessionLink cur = this.head;
        while (cur != null) {
            if(cur.getData().getSessionID() == id){
                return cur.getData();
            }
            cur = cur.getNext();
        }
        return null;
    }

    // add session to the start
    public void addFirst(Session s){
        this.head = new SessionLink(s,this.head);
    }

    // add session to the end
    public void addEnd(Session s){
            SessionLink cur = this.head;
            while (cur.getNext() != null) {
                cur = cur.getNext();
            }
            cur.setNext(new SessionLink(s, null));
    }

    // add session to the correct location based on ID
    public boolean insertAfter(Session s){
        SessionLink cur = this.head;
        while(cur.getNext() != null) {
            if (s.getSessionID() < cur.getNext().getData().getSessionID()) {
                SessionLink sl = new SessionLink(s, cur.getNext());
                cur.setNext(sl);
                return true;
            }else{
                cur = cur.getNext();
            }
        }
        return false;
    }

    // handle the adding of a session, decide which of the above 3 methods to use
    public SessionList handleAdd(Session s){
        if(this.head == null){
            this.head = new SessionLink(s,null);
            return this;
        }else{
            if(s.getSessionID() < this.head.getData().getSessionID()){
                this.addFirst(s);
                return this;
            }

            // try to insert based on lesser ID
            boolean didInsert = this.insertAfter(s);

            if(didInsert){
                return this;
            }else{
                // if it didnt insert its the last element
                this.addEnd(s);
                return this;
            }
        }
    }

    // search and create a string for the info of all sessions matching search parameters.
    public String search(String mentor, int id, boolean hasId){
        String str = "";
        int countMentor = 0;
        int countId = 0;
        SessionLink cur = this.head;
        while(cur != null){
                Session s = cur.getData();
                boolean add = false;
            if(hasId && s.getSessionID() == id) {
                add = true;
                countId++;
            }
            if(!mentor.isEmpty() && s.getMentor().contains(mentor)){
                add = true;
                countMentor++;
            }
            if(add){
                str += s.toString();
                str += "\n\n";
            }
            cur = cur.getNext();
        }

        if(hasId && countId == 0){
            str += "Session "+id+" not found\n\n";
        }
        if(!mentor.isEmpty() && countMentor == 0){
            str += "No session found for mentor "+mentor;
        }
        return str;
    }

    // remove a session by ID
    public boolean removeById(int id){
        if(this.head != null && this.head.getData().getSessionID() == id){
            this.head = this.head.getNext();
            return true;
        }
        SessionLink cur = this.head;
        while(cur != null && cur.getNext() != null){
            if(cur.getNext().getData().getSessionID() == id){
                cur.setNext(cur.getNext().getNext());
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }

    // try to add a participant to a session by ID, if the session is full return false
    public boolean registerParticipant(int id){
        SessionLink cur = this.head;
        while(cur != null){
            if(cur.getData().getSessionID() == id){
                if(cur.getData().getCurrentParticipants() < cur.getData().getMaxParticipants()) {
                    cur.getData().setCurrentParticipants(cur.getData().getCurrentParticipants() + 1);
                    return true;
                }else{
                    return false;
                }
            }
            cur = cur.getNext();
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        SessionLink cur = this.head;
        while(cur != null){
            Session s = cur.getData();
            str += s.toString();
            str += "\n\n";
            cur = cur.getNext();
        }
        return str;
    }
}
