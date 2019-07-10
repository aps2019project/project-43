package GamePackage;

class ComputerClientInfo extends ClientInfo {

    ComputerClientInfo(String deck){
        new ComputerAccount(deck, this);
    }

    @Override
    void send(NetworkObject runnable) {
    }
}
