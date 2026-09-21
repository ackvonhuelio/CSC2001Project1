public class MyLinkedList {
    private Session data;
    private MyLinkedList next;

    // constructors
    public MyLinkedList() {
        this.next = null;
        this.data = null;
    }
    public MyLinkedList(Session h, MyLinkedList n){
        this.next = n;
        this.data = h;
    }

    // get the Session by ID
    public Session grabById(int id){
        if(this == null){
            return null;
        }
        if(this.data == null){
            return null;
        }
        MyLinkedList cur = this;
        while (cur != null) {
            if(cur.data.getSessionID() == id){
                return cur.data;
            }
            cur = cur.next;
        }
        return null;
    }

    // add session to the start
    public MyLinkedList addFirst(Session s){
        if(this.data == null){
            return new MyLinkedList(s, null);
        }
        return new MyLinkedList(s,this);
    }

    // add session to the end
    public MyLinkedList addEnd(Session s){
            if(this.data == null){
                return new MyLinkedList(s, null);
            }
            MyLinkedList cur = this;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new MyLinkedList(s, null);
            return this;
    }

    // add session to the correct location based on ID
    public MyLinkedList insertAfter(Session s){
        if(this.data == null){
            return new MyLinkedList(s, null);
        }
        if(s.getSessionID() < this.data.getSessionID()){
            return new MyLinkedList(s,this);
        }
        MyLinkedList cur = this;
        while(cur.next != null) {
            if (s.getSessionID() < cur.next.data.getSessionID()) {
                MyLinkedList sl = new MyLinkedList(s, cur.next);
                cur.next = sl;
                return this;
            }else{
                cur = cur.next;
            }
        }
        cur.next = new MyLinkedList(s, null);
        return this;
    }

    // handle the adding of a session, decide which of the above 3 methods to use
    public MyLinkedList handleAdd(Session s){
        // TODO
        if(this.data == null || s.getSessionID() < this.data.getSessionID()){
            return this.addFirst(s);
        }
        MyLinkedList cur = this;
        while(cur.next != null){
            cur = cur.next;
        }
        if(s.getSessionID() > cur.data.getSessionID()){
            return this.addEnd(s);
        }
        return this.insertAfter(s);
    }

    public String searchIByID(int id){
        return this.search("",id,true);
    }
    public String searchByMentor(String mentor){
        return this.search(mentor,0,false);
    }

    // search and create a string for the info of all sessions matching search parameters.
    public String search(String mentor, int id, boolean hasId){
        String str = "";
        int countMentor = 0;
        int countId = 0;
        MyLinkedList cur = this;
        while(cur != null){
                Session s = cur.data;
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
            cur = cur.next;
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
    public MyLinkedList removeById(int id){
        if(this != null && this.data.getSessionID() == id){
            if(this.next != null) {
                this.data = this.next.data;
                this.next = this.next.next;
            }else{
                this.data = null;
            }
            return this;
        }

        MyLinkedList cur = this;
        while(cur != null && cur.next != null){
            if(cur.next.data.getSessionID() == id){
                cur.next = cur.next.next;
                return this;
            }
            cur = cur.next;
        }
        return this;
    }

    // remove by session
    public MyLinkedList remove(Session s){
        if(s == null){
            return null;
        }
        return this.removeById(s.getSessionID());
    }

    // try to add a participant to a session by ID, if the session is full return false
    public boolean registerParticipant(int id){
        MyLinkedList cur = this;
        while(cur != null){
            if(cur.data.getSessionID() == id){
                if(cur.data.getCurrentParticipants() < cur.data.getMaxParticipants()) {
                    cur.data.setCurrentParticipants(cur.data.getCurrentParticipants() + 1);
                    return true;
                }else{
                    return false;
                }
            }
            cur = cur.next;
        }
        return false;
    }

    // try to remove a participant to a session by ID, if the session is empty return false
    public boolean unregisterParticipant(int id){
        MyLinkedList cur = this;
        while(cur != null){
            if(cur.data.getSessionID() == id){
                if(cur.data.getCurrentParticipants() > 0) {
                    cur.data.setCurrentParticipants(cur.data.getCurrentParticipants() - 1);
                    return true;
                }else{
                    return false;
                }
            }
            cur = cur.next;
        }
        return false;
    }


    public void display(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String str = "";
        MyLinkedList cur = this;
        while(cur != null){
            Session s = cur.data;
            str += s.toString();
            str += "\n\n";
            cur = cur.next;
        }
        return str;
    }
}
