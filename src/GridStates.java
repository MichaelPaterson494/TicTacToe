public enum GridStates {
    EMPTY('_'), PLAYER1('X'), PLAYER2('O');

    char state;

    private GridStates(char state)
    {
        this.state = state;
    }

    public char getState(){
        return this.state;
    }

    public String toString(){
        switch(this){
            case PLAYER1 :
                return "Player One";
            case PLAYER2 :
                return "Player Two";
            default      :
                return "Noone";
        }
    }
}
