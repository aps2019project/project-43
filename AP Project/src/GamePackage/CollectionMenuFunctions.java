package GamePackage;

import java.util.concurrent.atomic.AtomicInteger;


public enum CollectionMenuFunctions {
    SHOW {
        @Override
        public void doSomething(String[] userInput) {
            ((AccountMenu) AccountMenu.getAccountMenu()).getLoggedAccount().getCollection().print();
//            World.getInstance().show();
            //should define a show method in Collection(print)
        }
    },
    SEARCH {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().search(userInput[1]);
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
            AccountMenu.getLoggedAccount().createDeck(userInput[2]);
        }
    },
    DELETE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().deleteDeck(userInput[2]);
        }
    },
    ADD {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().addObjectToDeck(userInput[1], userInput[4]);
        }
    },
    REMOVE {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().removeObjectFromDeck(userInput[1], userInput[4]);
        }
    },
    VALIDATE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().getDeck(userInput[2]).validate();
        }
    },
    SELECT_DECK {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().setMainDeck(userInput[2]);
        }
    },
    SHOW_ALL_DECKS {
        @Override
        public void doSomething(String[] userInput) {
            AccountMenu.getLoggedAccount().showAllDecks();
        }
    },
    SHOW_DECK {
        @Override
        public void doSomething(String[] userInput) {
            //todo
//            World.getInstance().showDeck(userInput[1]);
        }
    },
    HELP {
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