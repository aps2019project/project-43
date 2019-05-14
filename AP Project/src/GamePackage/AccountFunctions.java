package GamePackage;

public enum AccountFunctions {
    CREATE_ACCOUNT {
        @Override
        public void runFunc(String[] input) {
            World.getInstance().createAccount(input[2]);
        }
    },
    LOGIN {
        @Override
        public void runFunc(String[] input) {
            World.getInstance().login(input[1]);
        }
    },
    SHOW_LEADERBOARD {
        @Override
        public void runFunc(String[] input) {
            World.getInstance().showLeaderBoard();
        }
    },
    SAVE {
        @Override
        public void runFunc(String[] input) {
            World.getInstance().save();
        }
    },
    LOGOUT {
        @Override
        public void runFunc(String[] input) {
            World.getInstance().logout();
        }
    },
    HELP {
        @Override
        public void runFunc(String[] input) {
            AccountFunctions.showMenu();
        }
    },
    INVALID {
        @Override
        public void runFunc(String[] input) {
            System.out.println("invalid command");
        }
    };

    public abstract void runFunc(String[] input);

    private AccountFunctions state;

    public AccountFunctions getState() {
        return state;
    }

    public void setState(String[] input) {
        try {
            if (input.length == 1) {
                state = AccountFunctions.valueOf(input[0]);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            if (input[0].equals("SHOW")) {
                state = SHOW_LEADERBOARD;
            } else if (input[0].equals("CREATE")) {
                state = CREATE_ACCOUNT;
            } else if (input[0].equals("LOGIN")){
                state = LOGIN;
            }else{
                state = INVALID;
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. create account");
        System.out.println("2. login");
        System.out.println("3. show leaderboard");
        System.out.println("4. save");
        System.out.println("5. logout");
        System.out.println("6. help");
    }
}
