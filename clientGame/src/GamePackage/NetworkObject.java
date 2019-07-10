package GamePackage;

import java.io.Serializable;

public class NetworkObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int clientId;
    private String command;
    private String authToken;
    private Type type;

    public NetworkObject(Type type, int clientId, String command, String authToken) {
        this.clientId = clientId;
        this.command = command;
        this.authToken = authToken;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getClientId() {
        return clientId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getCommand() {
        return command;
    }

    public enum Type {
        COMMAND, LOGIN, LOGOUT, EXIT, CHAT, PRINT, SWITCH, WAIT_OR_CANCEL, FRIEND_JOINED, CANCEL_WAIT, END_CHAT
    }

    @Override
    public String toString() {
        return type + " " + command;
    }
}
