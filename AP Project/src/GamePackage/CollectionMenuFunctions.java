package GamePackage;

public enum CollectionMenuFunctions {
    SHOW {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().getCollection().print();
        }
    },
    SEARCH {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().getCollection().search(userInput[1]);
        }
    },
    SAVE {
        @Override
        public void doSomething(String[] userInput) {
            //todo
        }
    },
    CREATE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().createDeck(userInput[2]);
        }
    },
    DELETE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().deleteDeck(userInput[2]);
        }
    },
    ADD {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().addObjectToDeck(userInput[1], userInput[4]);
        }
    },
    REMOVE {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().removeObjectFromDeck(userInput[1], userInput[4]);
        }
    },
    VALIDATE_DECK {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().validateDeck(userInput[2]);
        }
    },
    SELECT_DECK {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().setMainDeck(userInput[2]);
        }
    },
    SHOW_ALL_DECKS {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().showAllDecks();
        }
    },
    SHOW_DECK {
        @Override
        public void doSomething(String[] userInput) {
            Account.getLoggedAccount().showDeck(userInput[2]);
        }
    };

    public abstract void doSomething(String[] userInput);

}