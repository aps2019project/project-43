package GamePackage;

public enum MainMenuFunctions {
    COLLECTION {
        @Override
        public void enter() {
        }
    },
    SHOP {
        @Override
        public void enter() {
        }
    },
    BATTLE {
        @Override
        public void enter() {
        }
    },
    EXIT {
        @Override
        public void enter() {
        }
    },
    HELP {
        @Override
        public void enter() {
            MainMenuFunctions.showMenu();
        }
    },
    INVALID {
        @Override
        public void enter() {
            System.out.println("invalid command");
        }
    };

    public abstract void enter();

    private MainMenuFunctions state;

    public MainMenuFunctions getState() {
        return state;
    }

    public void setState(String[] input) {
        try {
            if (input.length == 1) {
                state = MainMenuFunctions.valueOf(input[0]);
            } else if (input.length == 2) {
                state = MainMenuFunctions.valueOf(input[1]);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            state = INVALID;
        }
    }

    public static void showMenu() {
        System.out.println("1. Collection");
        System.out.println("2. Shop");
        System.out.println("3. Battle");
        System.out.println("4. Exit");
        System.out.println("5. Help");
    }
}
