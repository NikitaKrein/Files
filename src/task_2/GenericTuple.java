package task_2;

public final class GenericTuple<F, S> {
    private F first;
    private S second;

    public GenericTuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return " Directories = " + first + ", files = " + second + ")";
    }

}