package genericExample;

public class WildGeneric<W> {
    W wildcard;
    public void setWildcard(W wildcard) {
        this.wildcard=wildcard;
    }

    public W getWildcard(){
        return wildcard;
    }
}
