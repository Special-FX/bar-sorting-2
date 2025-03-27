public class Step { //my code (doesn't work)
    //constants
    public static final int COMPARE = 0;
    public static final int SWAP = 1;
    public static final int TOBUFFER = 3;
    public static final int TOBARS = 4;

    //fields
    private final int index1;
    private final int index2;
    private final int type;

    //constructor
    public Step(int index1, int index2, int type) {
        this.index1 = index1;
        this.index2 = index2;
        this.type = type;
    }

    //methods
    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getType() {
        return type;
    }
}

/*
public class Step { //alt code
    //constants
    public static final int COMPARE = 0;
    public static final int SWAP = 1;
    public static final int TOBUFFER = 3;
    public static final int TOBARS = 4;

    //fields
    private int index1, index2, type;

    //constructors
    public Step(int index1, int index2, int type) {
        this.index1 = index1;
        this.index2 = index2;
        this.type = type;
    }

    //methods
    public int getIndex1() {
        return index1;
    }
    public int getIndex2() {
        return index2;
    }
    public int getType() {
        return type;
    }
}
*/