package GamePackage;

public enum CollectionMenuFunctions {
    SHOW {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().show();
        }
    },
    SEARCH {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().search(userInput[1]);
        }
    },
    SAVE {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    CREATE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().createDeck(userInput[2]);
        }
    },
    DELETE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().deleteDeck(userInput[2]);
        }
    },
    ADD {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    REMOVE {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    VALIDATE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().validateDeck(userInput[2]);
        }
    },
    SELECT_DECK {
        @Override
        public void doSomething(String[] userInput) {
            World.getInstance().selectMainDeck(userInput[2]);
        }
    },
    SHOW_ALL_DECKS {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    SHOW_DECK {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    HELP {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    EXIT {
        @Override
        public void doSomething(String[] userInput) {
        }
    },
    INVALID {
        @Override
        public void doSomething(String[] userInput) {
        }
    };

    public abstract void doSomething(String[] userInput);

    private CollectionMenuFunctions state;

    public CollectionMenuFunctions getState() {
        return state;
    }

    public void setState(String[] input) {
    }
}
