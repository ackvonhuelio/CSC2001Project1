public class SessionLink {
    private Session data;
    private SessionLink next;

    // constructors
    public SessionLink(Session s, SessionLink n){
        this.data = s;
        this.next = n;
    }

    // getters
    public Session getData(){
        return this.data;
    }
    public SessionLink getNext(){
        return this.next;
    }
    // setters
    public void setNext(SessionLink n){
        this.next = n;
    }
}
