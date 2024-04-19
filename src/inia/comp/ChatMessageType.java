package inia.comp;

public enum ChatMessageType {
    
    MESSAGE((byte) 0),
    ACTIONBAR((byte) 2),
    ;

    private byte data;

    ChatMessageType(byte data) {
        this.data = data;
    }

    public byte getData() {
        return this.data;
    }

    public void setData(byte data) {
        this.data = data;
    }
}
