package GamePackage;

public enum ShopFunctions {
    SHOW {
        @Override
        public void doSomething(String[] input) {
            AccountMenu.getLoggedAccount().getShop().show();
        }
    },
    SHOW_COLLECTION {
        @Override
        public void doSomething(String[] input) {
            AccountMenu.getLoggedAccount().getShop().showCollection();
        }
    },
    SEARCH {
        @Override
        public void doSomething(String[] input) {
            AccountMenu.getLoggedAccount().getShop().search(input[1]);
        }
    },
    SEARCH_COLLECTION {
        @Override
        public void doSomething(String[] input) {
            AccountMenu.getLoggedAccount().getShop().searchCollection(input[2]);
        }
    },
    BUY {
        @Override
        public void doSomething(String[] input) {
            AccountMenu.getLoggedAccount().getShop().buyCard(input[1]);
        }
    },
    SELL {
        @Override
        public void doSomething(String[] input) {
            for (int i = 1; i < input.length; i++) {
                AccountMenu.getLoggedAccount().getShop().sellCard(Integer.parseInt(input[i]));
            }
        }
    },
    HELP {
        @Override
        public void doSomething(String[] input) {
            ShopFunctions.showHelp();
        }
    },
    EXIT {
        @Override
        public void doSomething(String[] input) {
            MainMenu.goToMainMenu();
        }
    },
    INVALID {
        @Override
        public void doSomething(String[] input) {
            System.out.println("Invalid Input");
        }
    };

    public abstract void doSomething(String[] input);

    private ShopFunctions state;

    public ShopFunctions getState() {
        return state;
    }

    public void setState(String[] input) {
        try {
            if (input.length == 1) {
                state = ShopFunctions.valueOf(input[0].toUpperCase());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            switch (input[0].toUpperCase()) {
                case "SELL": {
                    state = ShopFunctions.valueOf(input[0].toUpperCase());
                    break;
                }
                case "BUY": {
                    state = ShopFunctions.valueOf(input[0].toUpperCase());
                    break;
                }
                case "SEARCH": {
                    if (input[1].equalsIgnoreCase("COLLECTION")) {
                        state = ShopFunctions.SEARCH_COLLECTION;
                    } else {
                        state = ShopFunctions.SEARCH;
                    }
                    break;
                }
                case "SHOW": {
                    if (input[1].equalsIgnoreCase("COLLECTION")) {
                        state = ShopFunctions.SHOW_COLLECTION;
                    } else {
                        state = ShopFunctions.SHOW;
                    }
                    break;
                }case "HELP":{
                    state = ShopFunctions.HELP;
                }
                default:
                    state = INVALID;
            }
        }
    }

    public static void showHelp() {
        System.out.println("1. Show");
        System.out.println("2. Show collection");
        System.out.println("3. Search[item name|card name]");
        System.out.println("4. Search collection[item name|card name]");
        System.out.println("5. Buy[card name|item name]");
        System.out.println("6. Sell[card ID]");
        System.out.println("7. Exit");
        System.out.println("8. help");
    }

}


