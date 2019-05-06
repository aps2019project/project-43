package GamePackage;

public enum ShopFunctions {
    SHOW{
        @Override
        public void dosth(String[] input) {

        }
    },
    SHOW_COLLECTION{
        @Override
        public void dosth(String[] input) {

        }
    },
    SEARCH{
        @Override
        public void dosth(String[] input) {

        }
    },
    SEARCH_COLLECTION{
        @Override
        public void dosth(String[] input) {

        }
    },
    BUY{
        @Override
        public void dosth(String[] input) {

        }
    },
    SELL{
        @Override
        public void dosth(String[] input) {

        }
    },
    HELP{
        @Override
        public void dosth(String[] input) {
            showHelp();
        }
    },
    EXIT{
        @Override
        public void dosth(String[] input) {

        }
    },
    INVALID{
        @Override
        public void dosth(String[] input) {
            System.out.println("Invalid Input");
        }
    };

    public abstract void dosth(String[] input);

    private ShopFunctions state;

    public ShopFunctions getState() {
        return state;
    }

    public void setState(String[] input){
        if(input.length == 1){
            state = ShopFunctions.valueOf(input[0].toUpperCase());
        }else{
            switch (input[0].toUpperCase()){
                case "SELL":{
                    state = ShopFunctions.valueOf(input[0].toUpperCase());
                    break;
                }
                case "BUY":{
                    state = ShopFunctions.valueOf(input[0].toUpperCase());
                    break;
                }
                case "SEARCH":{
                    if(input[1].toUpperCase().equals("COLLECTION")){
                        state = ShopFunctions.valueOf("SEARCH COLLECTION");
                    }else{
                        state = ShopFunctions.valueOf("SEARCH");
                    }break;
                }
                case "SHOW":{
                    if (input[1].toUpperCase().equals("COLLECTION")){
                        state = ShopFunctions.valueOf("SHOW COLLECTION");
                    }else{
                        state = ShopFunctions.valueOf("SHOW");
                    }break;
                }
            }
        }
    }

    public void showHelp(){
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


