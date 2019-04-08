package GamePackage;

public enum AccountFunctions {
    CREATE_ACCOUNT {
        @Override
        public void runFunc(String[] input) {
        }
    },
    LOGIN {
        @Override
        public void runFunc(String[] input) {
        }
    },
    SHOW_LEADERBOARD {
        @Override
        public void runFunc(String[] input) {
        }
    },
    SAVE {
        @Override
        public void runFunc(String[] input) {
        }
    },
    LOGOUT {
        @Override
        public void runFunc(String[] input) {
        }
    },
    HELP {
        @Override
        public void runFunc(String[] input) {
        }
    },
    INVALID {
        @Override
        public void runFunc(String[] input) {
            System.out.println("invalid option");
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
            } else {
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
