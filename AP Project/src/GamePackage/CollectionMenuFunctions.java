package GamePackage;

public enum CollectionMenuFunctions {
    SHOW {

    },
    SEARCH {

    },
    SAVE {

    },
    CREATE_DECK {

    },
    DELETE_DECK {

    },
    ADD {

    },
    REMOVE {

    },
    VALIDATE_DECK {

    },
    SELECT_DECK {

    },
    SHOW_ALL_DECKS {

    },
    SHOW_DECK {

    },
    HELP {

    },
    EXIT {

    },
    INVALID {

    };

    private CollectionMenuFunctions state;

    public CollectionMenuFunctions getState() {
        return state;
    }

    public void setState(String[] input) {
    }
}
