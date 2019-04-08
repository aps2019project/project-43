package GamePackage;

public enum MainMenuFunctions {
    COLLECTION {
        @Override
        public void enter(String[] input) {
        }
    },
    SHOP {
        @Override
        public void enter(String[] input) {
        }
    },
    BATTLE {
        @Override
        public void enter(String[] input) {
        }
    },
    EXIT {
        @Override
        public void enter(String[] input) {
        }
    },
    HELP {
        @Override
        public void enter(String[] input) {
        }
    },
    INVALID {
        @Override
        public void enter(String[] input) {
        }
    };

    public abstract void enter(String[] input);

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
}
